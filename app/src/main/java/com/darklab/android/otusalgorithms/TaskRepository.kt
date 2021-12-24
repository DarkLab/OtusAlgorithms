package com.darklab.android.otusalgorithms

import com.darklab.android.otusalgorithms.tasks.ITask
import com.darklab.android.otusalgorithms.tasks.TaskHashtable
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class TaskRepository @Inject constructor() {
    val requiredTask: ITask
        get() = TaskHashtable()
}