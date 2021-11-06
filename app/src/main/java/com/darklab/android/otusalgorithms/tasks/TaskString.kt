package com.darklab.android.otusalgorithms.tasks

class TaskString : ITask {
    override fun run(data: Array<String>): String {
        return data.first().length.toString()
    }
}