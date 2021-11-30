package com.darklab.android.otusalgorithms.tasks

class TaskDataStructure : ITask {
    override val rootPath: String = "taskdatastructure"

    override fun run(data: Array<String>): String {
        val array = DArray<Int>()
        array.add(25)
        return "<==>"
    }
}

class DArray<T> {
    private var array: Array<Any?>? = null
    var arrayT: Array<T>? = null

    fun add(element: T) {
        array = array?.let {
            it.copyOf(it.size + 1).apply {
                set(it.size, element)
            }
        } ?: run {
            Array(1) { element }
        }
    }

    @Suppress("UNCHECKED_CAST")
    fun get(index: Int): T {
        return array?.get(index) as? T ?: throw Exception("")
    }

    fun first(): T {
        return get(0)
    }

    fun removeLast() {

    }
}