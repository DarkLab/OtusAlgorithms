package com.darklab.android.otusalgorithms.tasks

class TaskPower : ITask {
    override val rootPath: String
        get() = "taskpower"

    override fun run(data: Array<String>): String {
        var error = ""
        val (number, power) = try {
            data[0].trim().toDouble() to data[1].trim().toLong()
        } catch (e: Exception) {
            error = "Ошибка: ${e.localizedMessage}"
            null to null
        }
        if (number == null || power == null) return error

        return binaryDecomposition(number, power).toString()
    }

    private val byIteration = { number: Double, power: Long ->
        var result = 1.0
        for (i in 1..power) {
            result *= number
        }
        result
    }

    private val powerOfTwo = { number: Double, power: Long ->
        var result = 1.0
        if (power > 0L) {
            var count = 1L
            result *= number
            while (count * 2 <= power) {
                result *= result
                count *= 2
            }
            do {
                result *= number
                count += 1
            } while (count < power)
        }
        result
    }

    private val binaryDecomposition = { number: Double, power: Long ->
        var p = power
        var acc = number
        var result = 1.0
        while (p > 0L) {
            val b = p % 2
            p /= 2
            if (b == 1L) {
                result *= acc
            }
            acc *= acc
        }
        result
    }
}