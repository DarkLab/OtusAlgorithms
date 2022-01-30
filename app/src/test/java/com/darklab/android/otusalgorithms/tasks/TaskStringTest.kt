package com.darklab.android.otusalgorithms.tasks

import org.junit.Assert.*

import org.junit.Test

class TaskStringTest {
    private val task = TaskString()

    @Test
    fun `test root path`() {
        assertEquals("taskstring", task.rootPath)
    }

    @Test
    fun `test run`() {
        task.run(
            arrayOf("Ticket")
        ).also {
            assertEquals("6", it)
        }

        task.run(
            arrayOf("Colorado")
        ).also {
            assertEquals("8", it)
        }
    }

    @Test
    fun `test empty word`() {
        task.run(
            arrayOf("")
        ).also {
            assertEquals("0", it)
        }
    }

    @Test
    fun `test 12345678`() {
        task.run(
            arrayOf("12345678")
        ).also {
            assertEquals("8", it)
        }
    }

    @Test
    fun `test big number`() {
        task.run(
            arrayOf("1234567890123456789012345678901234567890123456789012345678901234")
        ).also {
            assertEquals("64", it)
        }
    }

    @Test
    fun `test abcdefghijklmnoprstuvwxyz`() {
        task.run(
            arrayOf("abcdefghijklmnopqrstuvwxyz")
        ).also {
            assertEquals("26", it)
        }
    }

}