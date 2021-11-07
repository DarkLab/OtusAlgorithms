package com.darklab.android.otusalgorithms

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.darklab.android.otusalgorithms.tasks.ITask
import com.darklab.android.otusalgorithms.tasks.TaskString
import com.darklab.android.otusalgorithms.tasks.TaskTickets
import com.darklab.android.otusalgorithms.test.Tester
import kotlinx.coroutines.*

class MainActivity : AppCompatActivity() {
    private val scope = CoroutineScope(Dispatchers.Main)
    private var resultTextView: TextView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        resultTextView = findViewById(R.id.resultTextView)
    }

    override fun onResume() {
        super.onResume()

        val task = requiredTask()
        val rootPath = task.javaClass.simpleName.lowercase()

        scope.launch {
            printCurrentState(getString(R.string.processing))
            delay(1000)
            val result = withContext(Dispatchers.Default) {
                Tester(task, rootPath, assets).runTests()
            }

            printCurrentState(result)
        }
    }

    override fun onPause() {
        super.onPause()
        scope.cancel()
    }

    private fun printCurrentState(message: String) {
        resultTextView?.text = message
    }

    private fun requiredTask(): ITask = TaskTickets()
}