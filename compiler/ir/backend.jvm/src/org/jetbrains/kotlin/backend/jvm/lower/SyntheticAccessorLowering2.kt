/*
 * Copyright 2010-2018 JetBrains s.r.o. Use of this source code is governed by the Apache 2.0 license
 * that can be found in the license/LICENSE.txt file.
 */

package org.jetbrains.kotlin.backend.jvm.lower

import org.jetbrains.kotlin.backend.common.FileLoweringPass
import org.jetbrains.kotlin.backend.jvm.JvmBackendContext
import org.jetbrains.kotlin.backend.jvm.JvmLoweredDeclarationOrigin
import org.jetbrains.kotlin.backend.jvm.intrinsics.receiverAndArgs
import org.jetbrains.kotlin.codegen.*
import org.jetbrains.kotlin.codegen.context.CodegenContext
import org.jetbrains.kotlin.descriptors.*
import org.jetbrains.kotlin.descriptors.annotations.Annotations
import org.jetbrains.kotlin.descriptors.impl.ClassConstructorDescriptorImpl
import org.jetbrains.kotlin.descriptors.impl.ValueParameterDescriptorImpl
import org.jetbrains.kotlin.ir.IrElement
import org.jetbrains.kotlin.ir.IrStatement
import org.jetbrains.kotlin.ir.UNDEFINED_OFFSET
import org.jetbrains.kotlin.ir.declarations.IrClass
import org.jetbrains.kotlin.ir.declarations.IrDeclaration
import org.jetbrains.kotlin.ir.declarations.IrFile
import org.jetbrains.kotlin.ir.declarations.IrFunction
import org.jetbrains.kotlin.ir.declarations.impl.IrFunctionImpl
import org.jetbrains.kotlin.ir.expressions.IrCall
import org.jetbrains.kotlin.ir.expressions.IrDelegatingConstructorCall
import org.jetbrains.kotlin.ir.expressions.IrMemberAccessExpression
import org.jetbrains.kotlin.ir.expressions.impl.*
import org.jetbrains.kotlin.ir.symbols.IrClassSymbol
import org.jetbrains.kotlin.ir.symbols.IrConstructorSymbol
import org.jetbrains.kotlin.ir.symbols.impl.createFunctionSymbol
import org.jetbrains.kotlin.ir.util.usesDefaultArguments
import org.jetbrains.kotlin.ir.visitors.IrElementTransformerVoid
import org.jetbrains.kotlin.name.Name
import org.jetbrains.kotlin.resolve.DescriptorUtils
import org.jetbrains.kotlin.types.KotlinType
import java.util.*



class SyntheticAccessorLowering2(val context: JvmBackendContext) : IrElementTransformerVoid(), FileLoweringPass {

    private data class AccessorKey(
        val irFunction: IrFunction,
        val superQualifierSymbol: IrClassSymbol
    )

    private val state = context.state

    private val declarationStack = LinkedList<IrDeclaration>()
    private val classAccessors = mutableMapOf<IrClass, MutableMap<AccessorKey, IrFunction>>()

    var pendingTransformations = mutableListOf<Function0<Unit>>()

    private val IrClass.codegenContext
        get() = classAccessors[this]

    override fun lower(irFile: IrFile) {
        irFile.transform(this, null)
        pendingTransformations.forEach { it() }

        classAccessors.forEach { (clazz, accessors) ->
            lower(clazz, accessors)
        }
    }

    override fun visitDeclaration(declaration: IrDeclaration): IrStatement {
        declarationStack.push(declaration)
        return super.visitDeclaration(declaration).also { declarationStack.pop() }
    }

    override fun visitClass(declaration: IrClass): IrStatement {
        classAccessors.putIfAbsent(declaration, mutableMapOf())
        declaration.declarations.filterIsInstance<IrClass>().forEach {
            //Actually we only need to precalculate companion object context but could do it for all of them
            classAccessors.putIfAbsent(it, mutableMapOf())
        }
        return super.visitClass(declaration)
    }

    override fun visitFunction(declaration: IrFunction): IrStatement {
        if (declaration.isInline) declarationStack.push(declaration)
        val newFunction = super.visitFunction(declaration)
        assert(declarationStack.pop() == declaration)

    }

    private isAccessorRequired(irFunction: IrFunction): Boolean {
        irFun
    }

    private fun lower(irClass: IrClass, accessors: MutableMap<AccessorKey, IrFunction>) {
        val accessors = codegenContext.accessors
        val allAccessors =
            (
                    accessors.filterIsInstance<FunctionDescriptor>() +
                            accessors.filterIsInstance<AccessorForPropertyDescriptor>().flatMap {
                                listOfNotNull(
                                    if (it.isWithSyntheticGetterAccessor) it.getter else null,
                                    if (it.isWithSyntheticSetterAccessor) it.setter else null
                                )
                            }
                    ).filterIsInstance<AccessorForCallableDescriptor<*>>()

        val irClassToAddAccessor = data.irClass
        allAccessors.forEach { accessor ->
            addAccessorToClass(accessor, irClassToAddAccessor, context)
        }
    }


    override fun visitMemberAccess(expression: IrMemberAccessExpression): IrElement {
        val superResult = super.visitMemberAccess(expression)
        return createSyntheticAccessorCallForFunction(superResult, expression, context)
    }

    private fun createKey(irCall: IrFunction, superQualifierSymbol: IrClassSymbol) =
        AccessorKey(irCall.symbol.owner as IrFunction, superQualifierSymbol)

    companion object {
        fun createSyntheticAccessorCallForFunction(
            superResult: IrElement,
            expression: IrMemberAccessExpression,
            codegenContext: CodegenContext<*>?,
            context: JvmBackendContext
        ): IrElement {

            val descriptor = expression.descriptor
            if (descriptor is FunctionDescriptor && !expression.usesDefaultArguments()) {
                val directAccessor = codegenContext!!.accessibleDescriptor(
                    JvmCodegenUtil.getDirectMember(descriptor),
                    (expression as? IrCall)?.superQualifier
                )
                val accessor = actualAccessor(descriptor, directAccessor)

                if (accessor is AccessorForCallableDescriptor<*> && descriptor !is AccessorForCallableDescriptor<*>) {
                    val isConstructor = descriptor is ConstructorDescriptor
                    val accessorOwner = accessor.containingDeclaration as ClassOrPackageFragmentDescriptor
                    val accessorForIr =
                        accessorToIrAccessor(isConstructor, accessor, context, descriptor, accessorOwner) //TODO change call

                    val call =
                        if (isConstructor && expression is IrDelegatingConstructorCall)
                            IrDelegatingConstructorCallImpl(
                                expression.startOffset,
                                expression.endOffset,
                                accessorForIr as ClassConstructorDescriptor
                            )
                        else IrCallImpl(
                            expression.startOffset,
                            expression.endOffset,
                            accessorForIr,
                            emptyMap(),
                            expression.origin/*TODO super*/
                        )
                    //copyAllArgsToValueParams(call, expression)
                    val receiverAndArgs = expression.receiverAndArgs()
                    receiverAndArgs.forEachIndexed { i, irExpression ->
                        call.putValueArgument(i, irExpression)
                    }
                    if (isConstructor) {
                        call.putValueArgument(
                            receiverAndArgs.size,
                            IrConstImpl.constNull(
                                UNDEFINED_OFFSET,
                                UNDEFINED_OFFSET,
                                context.ir.symbols.defaultConstructorMarker.descriptor.defaultType
                            )
                        )
                    }
                    return call
                }
            }
            return superResult
        }

        private fun accessorToIrAccessor(
            isConstructor: Boolean,
            accessor: CallableMemberDescriptor,
            context: JvmBackendContext,
            descriptor: FunctionDescriptor,
            accessorOwner: ClassOrPackageFragmentDescriptor
        ): FunctionDescriptor {
            return if (isConstructor)
                (accessor as AccessorForConstructorDescriptor).constructorDescriptorWithMarker(
                    context.ir.symbols.defaultConstructorMarker.descriptor.defaultType
                )
            else descriptor.toStatic(
                accessorOwner,
                Name.identifier(context.state.typeMapper.mapAsmMethod(accessor as FunctionDescriptor).name)
            )
        }

        fun addAccessorToClass(accessor: AccessorForCallableDescriptor<*>, irClassToAddAccessor: IrClass, context: JvmBackendContext) {
            val accessorOwner = (accessor as FunctionDescriptor).containingDeclaration as ClassOrPackageFragmentDescriptor
            val body = IrBlockBodyImpl(UNDEFINED_OFFSET, UNDEFINED_OFFSET)
            val isConstructor = accessor.calleeDescriptor is ConstructorDescriptor
            val accessorForIr = accessorToIrAccessor(
                isConstructor, accessor, context,
                accessor.calleeDescriptor as? FunctionDescriptor ?: return,
                accessorOwner
            )
            val syntheticFunction = IrFunctionImpl(
                UNDEFINED_OFFSET, UNDEFINED_OFFSET, JvmLoweredDeclarationOrigin.SYNTHETIC_ACCESSOR,
                accessorForIr, body
            )
            val calleeDescriptor = accessor.calleeDescriptor as FunctionDescriptor
            val delegationCall =
                if (!isConstructor)
                    IrCallImpl(UNDEFINED_OFFSET, UNDEFINED_OFFSET, calleeDescriptor)
                else IrDelegatingConstructorCallImpl(
                    UNDEFINED_OFFSET, UNDEFINED_OFFSET,
                    createFunctionSymbol(accessor.calleeDescriptor) as IrConstructorSymbol,
                    accessor.calleeDescriptor as ClassConstructorDescriptor
                )
            copyAllArgsToValueParams(delegationCall, accessorForIr)

            body.statements.add(
                if (isConstructor) delegationCall else IrReturnImpl(
                    UNDEFINED_OFFSET,
                    UNDEFINED_OFFSET,
                    accessor,
                    delegationCall
                )
            )
            irClassToAddAccessor.declarations.add(syntheticFunction)
        }

        private fun actualAccessor(descriptor: FunctionDescriptor, calculatedAccessor: CallableMemberDescriptor): CallableMemberDescriptor {
            if (calculatedAccessor is AccessorForPropertyDescriptor) {
                val isGetter = descriptor is PropertyGetterDescriptor
                val propertyAccessor = if (isGetter) calculatedAccessor.getter!! else calculatedAccessor.setter!!
                if (isGetter && calculatedAccessor.isWithSyntheticGetterAccessor || !isGetter && calculatedAccessor.isWithSyntheticSetterAccessor) {
                    return propertyAccessor
                }
                return descriptor

            }
            return calculatedAccessor
        }

        private fun copyAllArgsToValueParams(call: IrMemberAccessExpression, fromDescriptor: CallableMemberDescriptor) {
            var offset = 0
            val newDescriptor = call.descriptor
            newDescriptor.dispatchReceiverParameter?.let {
                call.dispatchReceiver = IrGetValueImpl(UNDEFINED_OFFSET, UNDEFINED_OFFSET, fromDescriptor.valueParameters[offset++])
            }

            newDescriptor.extensionReceiverParameter?.let {
                call.extensionReceiver = IrGetValueImpl(UNDEFINED_OFFSET, UNDEFINED_OFFSET, fromDescriptor.valueParameters[offset++])
            }

            call.descriptor.valueParameters.forEachIndexed { i, _ ->
                call.putValueArgument(i, IrGetValueImpl(UNDEFINED_OFFSET, UNDEFINED_OFFSET, fromDescriptor.valueParameters[i + offset]))
            }
        }

        private fun AccessorForConstructorDescriptor.constructorDescriptorWithMarker(marker: KotlinType) =
            ClassConstructorDescriptorImpl.createSynthesized(containingDeclaration, annotations, false, source).also {
                it.initialize(
                    DescriptorUtils.getReceiverParameterType(extensionReceiverParameter),
                    dispatchReceiverParameter,
                    emptyList()/*TODO*/,
                    calleeDescriptor.valueParameters.map {
                        it.copy(
                            this,
                            it.name,
                            it.index
                        )
                    } + ValueParameterDescriptorImpl.createWithDestructuringDeclarations(
                        it,
                        null,
                        calleeDescriptor.valueParameters.size,
                        Annotations.EMPTY,
                        Name.identifier("marker"),
                        marker,
                        false,
                        false,
                        false,
                        null,
                        SourceElement.NO_SOURCE,
                        null
                    ),
                    calleeDescriptor.returnType,
                    Modality.FINAL,
                    Visibilities.LOCAL
                )
            }
    }
}