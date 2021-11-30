package com.darklab.android.otusalgorithms.tasks

class TaskString : ITask {
    override val rootPath: String
        get() = "taskstring"

    override fun run(data: Array<String>): String {
        return data.first().length.toString()
    }
}