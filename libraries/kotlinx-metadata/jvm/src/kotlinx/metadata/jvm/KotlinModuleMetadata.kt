/*
 * Copyright 2010-2018 JetBrains s.r.o. Use of this source code is governed by the Apache 2.0 license
 * that can be found in the license/LICENSE.txt file.
 */

package kotlinx.metadata.jvm

import kotlinx.metadata.KmAnnotation
import kotlinx.metadata.ClassName
import kotlinx.metadata.InconsistentKotlinMetadataException
import org.jetbrains.kotlin.metadata.jvm.JvmModuleProtoBuf
import org.jetbrains.kotlin.metadata.jvm.deserialization.ModuleMapping
import org.jetbrains.kotlin.metadata.jvm.deserialization.PackageParts
import org.jetbrains.kotlin.metadata.jvm.deserialization.serializeToByteArray

class KotlinModuleMetadata(@Suppress("CanBeParameter", "MemberVisibilityCanBePrivate") val bytes: ByteArray) {
    internal val data: ModuleMapping = ModuleMapping.loadModuleMapping(bytes, javaClass.name, isVersionCompatible = { version ->
        // We only support metadata of version 1.1.* (this is Kotlin from 1.0 until today)
        version.getOrNull(0) == 1 && version.getOrNull(1) == 1
    }, skipMetadataVersionCheck = false, isJvmPackageNameSupported = true)

    class Writer : KmModuleVisitor() {
        private val b = JvmModuleProtoBuf.Module.newBuilder()

        override fun visitPackageParts(fqName: String, fileFacades: List<String>, multiFileClassParts: Map<String, String>) {
            PackageParts(fqName).apply {
                for (fileFacade in fileFacades) {
                    addPart(fileFacade, null)
                }
                for ((multiFileClassPart, multiFileFacade) in multiFileClassParts) {
                    addPart(multiFileClassPart, multiFileFacade)
                }

                addTo(b)
            }
        }

        override fun visitAnnotation(annotation: KmAnnotation) {
            /*
            // TODO: move StringTableImpl to module 'metadata' and support module annotations here
            b.addAnnotation(ProtoBuf.Annotation.newBuilder().apply {
                id = annotation.className.name // <-- use StringTableImpl here
            })
            */
        }

        fun write(metadataVersion: IntArray = KotlinClassHeader.COMPATIBLE_METADATA_VERSION): KotlinModuleMetadata =
            KotlinModuleMetadata(b.build().serializeToByteArray(metadataVersion))
    }

    fun accept(v: KmModuleVisitor) {
        for ((fqName, parts) in data.packageFqName2Parts) {
            val (fileFacades, multiFileClassParts) = parts.parts.partition { parts.getMultifileFacadeName(it) == null }
            v.visitPackageParts(fqName, fileFacades, multiFileClassParts.associate { it to parts.getMultifileFacadeName(it)!! })
        }

        for (annotation in data.moduleData.annotations) {
            v.visitAnnotation(KmAnnotation(annotation, emptyMap()))
        }

        v.visitEnd()
    }

    companion object {
        /**
         * Parses the given byte array with the .kotlin_module file content and returns the [KotlinModuleMetadata] instance,
         * or `null` if this byte array encodes a module with an unsupported metadata version.
         *
         * Throws [InconsistentKotlinMetadataException] if an error happened while parsing the given byte array,
         * which means that it's either not the content of a .kotlin_module file, or it has been corrupted.
         */
        @JvmStatic
        fun read(bytes: ByteArray): KotlinModuleMetadata? {
            try {
                val result = KotlinModuleMetadata(bytes)
                if (result.data == ModuleMapping.EMPTY) return null

                if (result.data == ModuleMapping.CORRUPTED) {
                    throw InconsistentKotlinMetadataException("Data doesn't look like the content of a .kotlin_module file")
                }

                return result
            } catch (e: InconsistentKotlinMetadataException) {
                throw e
            } catch (e: Throwable) {
                throw InconsistentKotlinMetadataException("Exception occurred when reading Kotlin metadata", e)
            }
        }
    }
}

abstract class KmModuleVisitor(private val delegate: KmModuleVisitor? = null) {
    /**
     * @param multiFileClassParts a map of multi-file class part name -> name of the corresponding multi-file facade
     */
    open fun visitPackageParts(fqName: String, fileFacades: List<String>, multiFileClassParts: Map<String, String>) {
        delegate?.visitPackageParts(fqName, fileFacades, multiFileClassParts)
    }

    open fun visitAnnotation(annotation: KmAnnotation) {
        delegate?.visitAnnotation(annotation)
    }

    open fun visitEnd() {
        delegate?.visitEnd()
    }

    // TODO: JvmPackageName
}
