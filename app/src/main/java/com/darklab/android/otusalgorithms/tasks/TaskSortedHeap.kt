package com.darklab.android.otusalgorithms.tasks

import android.util.Log

class TaskSortedHeap : ITask {
    override val rootPath: String
        get() = "tasksortedheap"

    override fun run(data: Array<String>): String {
        data.map {
            it.toInt()
        }.toIntArray()
        (1..15).toList().toIntArray().also {
            Log.d("=====", "array: ${it.joinToString()}")
        }.let {
            SortedHeap(it)
        }.also {
            Log.d("=====", "result: ${it.toArray().joinToString()}")
        }
        return "OK"
    }
}

class SortedHeap(elements: IntArray) {
    private var _array: IntArray = IntArray(0)

    init {
        _array = elements
        heapify()
    }

    fun toArray(): IntArray {
        return _array
    }

    private fun heapify() {
        val n = _array.lastIndex
        val i = (n - 1) / 2

        for (k in i downTo 0) {
            findMaxIndexWithRoot(k).takeIf { it != k }?.also { maxIndex ->
                swap(k, maxIndex)
            }
        }
    }

    private fun findMaxIndexWithRoot(rootIndex: Int): Int {
        val leftChildIndex = rootIndex * 2 + 1
        val rightChildIndex = rootIndex * 2 + 2

        val root = _array[rootIndex]
        val leftChild = _array[leftChildIndex]

        return if (rightChildIndex > _array.lastIndex) {
            if (root >= leftChild) rootIndex else leftChildIndex
        } else {
            val rightChild = _array[rightChildIndex]
            if (root >= leftChild) {
                if (root >= rightChild) rootIndex else rightChildIndex
            } else {
                if (leftChild >= rightChild) leftChildIndex else rightChildIndex
            }
        }
    }

    private fun swap(i1: Int, i2: Int) {
        val t = _array[i1]
        _array[i1] = _array[i2]
        _array[i2] = t
    }
}