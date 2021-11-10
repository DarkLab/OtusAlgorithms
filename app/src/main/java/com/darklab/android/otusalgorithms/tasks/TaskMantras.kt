package com.darklab.android.otusalgorithms.tasks

private const val BALL = "︎⚽︎"
private const val POINT = "・"

class TaskMantras : ITask {

    override fun run(data: Array<String>): String {
        return sayMantras()
    }

    private fun sayMantras(): String {
        val n = 24 // Random(System.currentTimeMillis()).nextInt(25)
        return sayMantra(n)
    }

    private fun sayMantra(n: Int): String {
        val stringBuilder = StringBuilder()
        for (x in 0 until 25) {
            for (y in 0 until 25) {
                stringBuilder.append(
                    if (mantra(n)(x, y)) BALL else POINT
                )
            }
            stringBuilder.append("\n")
        }
        return stringBuilder.toString()
    }

    private fun mantra(n: Int): (x: Int, y: Int) -> Boolean = when (n) {
        0 -> m0
        1 -> m1
        2 -> m2
        3 -> m3
        4 -> m4
        5 -> m5
        6 -> m6
        7 -> m7
        8 -> m8
        9 -> m9
        else -> m24
    }

    private val m0 = { x: Int, y: Int -> x * y < 100 }
    private val m1 = { x: Int, y: Int -> x * y == 0 }       // 08
    private val m2 = { x: Int, y: Int -> x == y }           // 02
    private val m3 = { x: Int, y: Int -> x * y % 6 < 3 }
    private val m4 = { x: Int, y: Int -> x * y % 6 < 4 }
    private val m5 = { x: Int, y: Int -> x * y % 4 < 3 }
    private val m6 = { x: Int, y: Int -> x * y % 6 < 2 }
    private val m7 = { x: Int, y: Int -> x + y == 24 }      // 03
    private val m8 = { x: Int, y: Int -> (x + y) % 2 == 0 } // 20
    private val m9 = { x: Int, y: Int -> x < 8 || y < 8 }
    private val m10 = { x: Int, y: Int -> x * y % 4 < 3 }
    private val m11 = { x: Int, y: Int -> x * y % 4 < 3 }
    private val m12 = { x: Int, y: Int -> x * y % 4 < 3 }
    private val m13 = { x: Int, y: Int -> x * y % 4 < 3 }
    private val m14 = { x: Int, y: Int -> x * y % 4 < 3 }
    private val m15 = { x: Int, y: Int -> x * y % 4 < 3 }
    private val m16 = { x: Int, y: Int -> x * y % 4 < 3 }
    private val m17 = { x: Int, y: Int -> x * y % 4 < 3 }
    private val m18 = { x: Int, y: Int -> x * y % 4 < 3 }
    private val m19 = { x: Int, y: Int -> x * y % 4 < 3 }
    private val m20 = { x: Int, y: Int -> x * y % 4 < 3 }
    private val m21 = { x: Int, y: Int -> x * y % 4 < 3 }
    private val m22 = { x: Int, y: Int -> x * y % 4 < 3 }
    private val m23 = { x: Int, y: Int -> x * y % 4 < 3 }
    private val m24 = { x: Int, y: Int -> x < 8 || y < 8 }
}