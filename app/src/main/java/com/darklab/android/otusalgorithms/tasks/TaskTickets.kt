package com.darklab.android.otusalgorithms.tasks

class TaskTickets : ITask {
    override fun run(data: Array<String>): String = when (data.first()) {
        "1" -> lucky1
        "2" -> lucky2
        "3" -> lucky3
        "4" -> lucky4
        else -> dummy
    }.invoke().toString()

    private val lucky1: () -> Int by lazy {
        {
            var count = 0
            for (a1 in 0..9)
                for (b1 in 0..9)
                    if (a1 == b1)
                        count++
            count
        }
    }

    private val lucky2: () -> Int by lazy {
        {
            var count = 0
            for (a1 in 0..9)
                for (a2 in 0..9)
                    for (b1 in 0..9)
                        for (b2 in 0..9)
                            if (a1 + a2 == b1 + b2)
                                count++
            count
        }
    }

    private val lucky3: () -> Int by lazy {
        {
            var count = 0
            for (a1 in 0..9)
                for (a2 in 0..9)
                    for (a3 in 0..9)
                        for (b1 in 0..9)
                            for (b2 in 0..9)
                                for (b3 in 0..9)
                                    if (a1 + a2 + a3 == b1 + b2 + b3)
                                        count++
            count
        }
    }

    private val lucky4: () -> Int by lazy {
        {
            var count = 0
            for (a1 in 0..9)
                for (a2 in 0..9)
                    for (a3 in 0..9)
                        for (a4 in 0..9)
                            for (b1 in 0..9)
                                for (b2 in 0..9)
                                    for (b3 in 0..9)
                                        for (b4 in 0..9)
                                            if (a1 + a2 + a3 + a4 == b1 + b2 + b3 + b4)
                                                count++
            count
        }
    }

    private val dummy: () -> String by lazy {
        {
            ""
        }
    }
}