package com.darklab.android.otusalgorithms.test

import com.darklab.android.otusalgorithms.tasks.ITask
import com.darklab.android.otusalgorithms.AssetsProvider

private const val TASK_BITS = "taskbits"
private val TASK_BITS_SUBTASK = arrayOf(
    "King",
    "Knight",
    "Bishop",
    "Rook",
    "Queen"
)

class Tester(
    private val task: ITask,
    private val assetsProvider: AssetsProvider
) {

    fun runTests(): String {
        val stringBuilder = StringBuilder()

        val rootPath =
            if (task.rootPath == TASK_BITS)
                "${task.rootPath}/${TASK_BITS_SUBTASK[0]}"
            else
                task.rootPath

        runSingleTask(rootPath, stringBuilder)

        return stringBuilder.toString()
    }

    private fun runSingleTask(
        rootPath: String,
        stringBuilder: StringBuilder
    ) {
        var nr = 0
        val files = assetsProvider.getAllAssetsIn(rootPath) ?: return
        while (true) {
            val inFile = "test.$nr.in"
            val outFile = "test.$nr.out"
            if (files.contains(inFile).not() || files.contains(outFile).not()) break

            val testResult = runTest("${rootPath}/$inFile", "${rootPath}/$outFile")
            val result = "Test #$nr - ${testResult.success} - ${testResult.elapsedTime} ms"
            stringBuilder.append(testResult.value).append("\n").append(result).append("\n")

            nr++
        }
    }

    private fun runTest(inFile: String, outFile: String): TestResult {
        val data = assetsProvider.getFileContent(inFile)
        val expect = assetsProvider.readResultFrom(outFile)
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