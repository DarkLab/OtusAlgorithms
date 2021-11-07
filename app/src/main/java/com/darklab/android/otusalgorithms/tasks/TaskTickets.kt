package com.darklab.android.otusalgorithms.tasks

class TaskTickets : ITask {
    override fun run(data: Array<String>): String = data.first().toInt().let { halfDigit ->
        numberOfTickets(halfDigit)
    }.toString()

    private fun numberOfTickets(halfDigit: Int): Long {
        var sum = 0L
        for (options in 0..(halfDigit * 9 + 1)) {
            val s = iterationWith(halfDigit, options)
            sum += (s * s)
        }
        return sum
    }

    private fun iterationWith(halfDigit: Int, options: Int): Long {
        if (halfDigit < 1) return 0
        if (halfDigit == 1) {
            return if (options < 10) 1 else 0
        }
        var sum = 0L
        for (l in 0..9) {
            if (options >= l) sum += iterationWith(halfDigit - 1, options - l)
        }
        return sum
    }
}