/*
 * Copyright 2010-2018 JetBrains s.r.o. Use of this source code is governed by the Apache 2.0 license
 * that can be found in the license/LICENSE.txt file.
 */

package org.jetbrains.kotlin.kotlinp.test

import com.intellij.openapi.Disposable
import org.jetbrains.kotlin.checkers.setupLanguageVersionSettingsForCompilerTests
import org.jetbrains.kotlin.cli.jvm.compiler.EnvironmentConfigFiles
import org.jetbrains.kotlin.cli.jvm.compiler.KotlinCoreEnvironment
import org.jetbrains.kotlin.codegen.GenerationUtils
import org.jetbrains.kotlin.kotlinp.Kotlinp
import org.jetbrains.kotlin.test.ConfigurationKind
import org.jetbrains.kotlin.test.KotlinTestUtils
import org.jetbrains.kotlin.test.TestJdkKind
import java.io.File
import kotlin.test.fail

fun compileAndPrintAllFiles(file: File, disposable: Disposable, tmpdir: File, compareWithTxt: Boolean) {
    val sb = StringBuilder()

    compile(file, disposable, tmpdir) { outputFile ->
        when (outputFile.extension) {
            "kotlin_module" -> {
                // TODO: support kotlin_module files
            }
            "class" -> {
                sb.appendFileName(outputFile.relativeTo(tmpdir))
                sb.append(Kotlinp.renderClassFile(Kotlinp.readClassFile(outputFile)!!))
            }
            else -> fail("Unknown file: $outputFile")
        }
    }

    if (compareWithTxt) {
        KotlinTestUtils.assertEqualsToFile(File(file.path.replace(".kt", ".txt")), sb.toString())
    }
}

private fun compile(file: File, disposable: Disposable, tmpdir: File, forEachOutputFile: (File) -> Unit) {
    val environment = KotlinCoreEnvironment.createForTests(
        disposable,
        KotlinTestUtils.newConfiguration(ConfigurationKind.ALL, TestJdkKind.MOCK_JDK),
        EnvironmentConfigFiles.JVM_CONFIG_FILES
    )
    setupLanguageVersionSettingsForCompilerTests(file.readText(), environment)
    val ktFile = KotlinTestUtils.createFile(file.name, file.readText(), environment.project)
    GenerationUtils.compileFileTo(ktFile, environment, tmpdir)

    for (outputFile in tmpdir.walkTopDown().sortedBy { it.nameWithoutExtension }) {
        if (outputFile.isFile) {
            forEachOutputFile(outputFile)
        }
    }
}

private fun StringBuilder.appendFileName(file: File) {
    appendln("// $file")
    appendln("// ------------------------------------------")
}
