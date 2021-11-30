package com.darklab.android.otusalgorithms

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ScrollView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.darklab.android.otusalgorithms.tasks.*
import com.darklab.android.otusalgorithms.test.Tester
import kotlinx.coroutines.*

class MainActivity : AppCompatActivity() {
    private val scope = CoroutineScope(Dispatchers.Main)
    private val commonResultTV by lazy { findViewById<TextView>(R.id.commonResultTV) }
    private val scrollContainer by lazy { findViewById<ScrollView>(R.id.scrollContainer) }
    private val mantrasTextView by lazy { findViewById<TextView>(R.id.mantrasTextView) }
    private val mantrasBtn by lazy { findViewById<Button>(R.id.mantrasBtn) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun onResume() {
        super.onResume()

        val task = requiredTask()

        scope.launch {
            printCurrentState(getString(R.string.processing))
            delay(1000)
            val result = withContext(Dispatchers.Default) {
                Tester(task, assets).runTests()
            }

            printCurrentState(result)
        }

        playWithMantras()
    }

    private fun playWithMantras() {
        mantrasBtn.setOnClickListener {
            scope.launch {
                scrollContainer.visibility = View.VISIBLE
                mantrasTextView.visibility = View.GONE
                printCurrentState(getString(R.string.processing))
                delay(1000)

                val t = TaskMantras()
                val result = withContext(Dispatchers.Default) {
                    Tester(
                        t,
                        assets
                    ).runTests()
                }
                scrollContainer.visibility = View.GONE
                mantrasTextView.text = result
                mantrasTextView.visibility = View.VISIBLE
            }
        }
    }

    override fun onPause() {
        super.onPause()
        scope.cancel()
    }

    private fun printCurrentState(message: String) {
        commonResultTV?.text = message
    }

    private fun requiredTask(): ITask = TaskSortedHeap()
}