package com.darklab.android.otusalgorithms.tasks

import kotlin.random.Random

private const val BALL = "︎⚽︎"
private const val POINT = "・"

class TaskMantras : ITask {
    override val rootPath: String
        get() = "taskmantras"

    override fun run(data: Array<String>): String {
        return sayRandomMantra()
    }

    private fun sayRandomMantra(): String {
        val n = Random(System.currentTimeMillis()).nextInt(14)
        return sayMantra(n)
    }

    private fun sayMantra(n: Int): String {
        val stringBuilder = StringBuilder()
        val currentMantra = mantra(n)
        for (x in 0 until 25) {
            for (y in 0 until 25) {
                stringBuilder.append(
                    if (currentMantra(x, y)) BALL else POINT
                )
            }
            stringBuilder.append("\n")
        }
        return stringBuilder.toString()
    }

    private fun mantra(n: Int): (x: Int, y: Int) -> Boolean = when (n) {
        0 -> { x: Int, y: Int -> x * y < 100 }
        1 -> { x: Int, y: Int -> x * y == 0 }       // 08
        2 -> { x: Int, y: Int -> x == y }           // 02
        3 -> { x: Int, y: Int -> x * y % 6 < 3 }
        4 -> { x: Int, y: Int -> x * y % 6 < 4 }
        5 -> { x: Int, y: Int -> x * y % 4 < 3 }
        6 -> { x: Int, y: Int -> x * y % 6 < 2 }
        7 -> { x: Int, y: Int -> x + y == 24 }      // 03
        8 -> { x: Int, y: Int -> (x + y) % 2 == 0 } // 20
        9 -> { x: Int, y: Int -> x < 8 || y < 8 }
        10 -> { x: Int, y: Int -> (x * y) % 3 == 0 }
        11 -> { x: Int, y: Int -> (x * y) % 5 == 0 }
        12 -> { x: Int, y: Int -> (x * y) % 10 == 0 }
        else -> { x: Int, y: Int -> x * y == 100 }
    }
}