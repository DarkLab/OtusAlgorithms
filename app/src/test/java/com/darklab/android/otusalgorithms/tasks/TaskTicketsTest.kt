package com.darklab.android.otusalgorithms.tasks

import org.junit.Assert.*

import org.junit.Test

class TaskTicketsTest {
    private val task = TaskTickets()

    @Test
    fun `test root path`() {
        assertEquals("tasktickets", task.rootPath)
    }

    @Test
    fun `check 1`() {
        task.run(
            arrayOf("1")
        ).also {
            assertEquals("10", it)
        }
    }

    @Test
    fun `check 2`() {
        task.run(
            arrayOf("2")
        ).also {
            assertEquals("670", it)
        }
    }

    @Test
    fun `check 3`() {
        task.run(
            arrayOf("3")
        ).also {
            assertEquals("55252", it)
        }
    }

    @Test
    fun `check 4`() {
        task.run(
            arrayOf("4")
        ).also {
            assertEquals("4816030", it)
        }
    }

    @Test
    fun `check 5`() {
        task.run(
            arrayOf("5")
        ).also {
            assertEquals("432457640", it)
        }
    }

}