package com.darklab.android.otusalgorithms.tasks

import android.util.Log
import java.util.*

class TaskHashtable : ITask {

    override val rootPath: String
        get() = "taskhashtable"

    override fun run(data: Array<String>): String {
        val ht = OtusHashtable<String, Int>()
        ht.put("dog", 40)
        Log.d("=====", "dog: ${ht.peek("dog")}")
        ht["cat"] = 25
        Log.d("=====", "cat: ${ht["cat"]}")
        ht["god"] = 30
        Log.d("=====", "god: ${ht["god"]}")
        Log.d("=====", "OtusHashtable: $ht")
        LinkedList<String>()
        return "OK"
    }
}

class OtusHashtable<K, V> {
    private var capacity = 10
    private var table = Array<OtusHashtableEntry<K, V>?>(capacity) { null }

    operator fun set(k: K, v: V) {
        val index = k.hashCode() % capacity
        OtusHashtableEntry(k, v).also { newEntry ->
            table[index]?.let {
                it.delete(k)
                newEntry.next = it
            }
            table[index] = newEntry
        }
    }

    fun put(k: K, v: V) {
        set(k, v)
    }

    operator fun get(k: K): V? {
        val index = k.hashCode() % capacity
        return table[index]?.find(k)?.value
    }

    fun peek(k: K): V? {
        return get(k)
    }

    override fun toString(): String {
        return "[${table.joinToString(", ")}]"
    }

    class OtusHashtableEntry<K, V>(private val key: K, val value: V) {
        var next: OtusHashtableEntry<K, V>? = null

        fun find(k: K): OtusHashtableEntry<K, V>? {
            var entry: OtusHashtableEntry<K, V>? = this
            while (entry != null) {
                if (entry.key == k) {
                    break
                } else {
                    entry = entry.next
                }
            }
            return entry
        }

        fun delete(k: K): OtusHashtableEntry<K, V>? {
            var parent: OtusHashtableEntry<K, V>? = null
            var entry: OtusHashtableEntry<K, V>? = this
            while (entry != null) {
                if (entry.key == k) {
                    if (parent != null) {
                        parent.next = entry.next
                    } else {
                        parent = entry.next
                    }
                    break
                } else {
                    parent = entry
                    entry = entry.next
                }
            }
            return parent
        }

        override fun toString(): String {
            val sb = StringBuilder()
            var entry: OtusHashtableEntry<K, V>? = this
            sb.append("{")
            while (entry != null) {
                sb.append("${entry.key} : ${entry.value}, ")
                entry = entry.next
            }
            sb.deleteCharAt(sb.lastIndex - 1)
            sb.append("}")
            return sb.toString()
        }
    }

}