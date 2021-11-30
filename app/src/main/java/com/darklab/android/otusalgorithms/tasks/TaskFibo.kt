package com.darklab.android.otusalgorithms.tasks

import kotlin.math.max

class TaskFibo : ITask {
    override val rootPath: String
        get() = "taskfibo"

    override fun run(data: Array<String>): String =
        data.first().toInt().let {
            fiboMatrix(it).toString()
        }

    private fun fiboRecursion(n: Int): Long {
        return if (n < 2) {
            n.toLong()
        } else {
            (fiboRecursion(n - 1) + fiboRecursion(n - 2))
        }
    }

    private fun fiboIteration(n: Int): Long {
        return if (n < 2) {
            n.toLong()
        } else {
            val a = longArrayOf(0L, 1L)

            for (i in 2..n) {
                val (i1, i2) = if (i % 2 == 0) 0 to 1 else 1 to 0
                a[i1] += a[i2]
            }
            max(a[0], a[1])
        }
    }

    private fun fiboGoldenRatio(n: Int): Long {
        return 0L
    }

    private fun fiboMatrix(n: Int): Long {
        return if (n < 2) {
            n.toLong()
        } else {
            var p = n - 1
            val startMatrix = longArrayOf(1L, 1L, 1L, 0L)
            var accMatrix = startMatrix.copyOf()
            var resultMatrix = startMatrix.copyOf()
            while (p > 1) {
                val b = p % 2
                p /= 2
                if (b == 1) {
                    resultMatrix = multiply(resultMatrix, accMatrix)
                }
                accMatrix = multiply(accMatrix, accMatrix)
            }
            resultMatrix[0]
        }
    }

    private fun multiply(matrix1: LongArray, matrix2: LongArray): LongArray {
        return longArrayOf(
            matrix1[0] * matrix2[0] + matrix1[1] * matrix2[2],
            matrix1[0] * matrix2[1] + matrix1[1] * matrix2[3],
            matrix1[2] * matrix2[0] + matrix1[3] * matrix2[2],
            matrix1[2] * matrix2[1] + matrix1[3] * matrix2[3]
        )
    }
}