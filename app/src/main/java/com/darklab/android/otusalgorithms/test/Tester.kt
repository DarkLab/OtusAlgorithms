package com.darklab.android.otusalgorithms.test

import android.content.res.AssetManager
import com.darklab.android.otusalgorithms.tasks.ITask

class Tester(
    private val task: ITask,
    private val rootPath: String,
    private val assets: AssetManager
) {

    fun runTests(): String {
        var nr = 0
        val files = assets.list(rootPath) ?: return "Файлы не найдены"
        val stringBuilder = StringBuilder()
        while (true) {
            val inFile = "test.$nr.in"
            val outFile = "test.$nr.out"
            if (files.contains(inFile).not() || files.contains(outFile).not()) break

            val testResult = runTest("$rootPath/$inFile", "$rootPath/$outFile")
            val result = "Test #$nr - ${testResult.success} - ${testResult.elapsedTime} ms"
            stringBuilder.append(testResult.value).append("\n").append(result).append("\n")

            nr++
        }

        return stringBuilder.toString()
    }

    private fun runTest(inFile: String, outFile: String): TestResult {
        val data = assets.open(inFile).bufferedReader().use {
            it.readLines().toTypedArray()
        }
        val expect = assets.open(outFile).bufferedReader().use {
            it.readText().trim()
        }
        val startTime = System.currentTimeMillis()
        val actual = task.run(data).trim()
        val endTime = System.currentTimeMillis()
        return TestResult(
            expect == actual,
            endTime - startTime,
            actual
        )
    }

    data class TestResult(
        val success: Boolean,
        val elapsedTime: Long,
        val value: String
    )
}