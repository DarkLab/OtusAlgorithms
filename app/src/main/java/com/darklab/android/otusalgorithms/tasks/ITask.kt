package com.darklab.android.otusalgorithms.tasks

interface ITask {
    val rootPath: String
    fun run(data: Array<String>): String
}