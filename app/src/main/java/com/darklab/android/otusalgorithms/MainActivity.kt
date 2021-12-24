package com.darklab.android.otusalgorithms

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ScrollView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

class MainActivity : AppCompatActivity() {
    @Inject
    lateinit var viewModel: CurrentTaskViewModel
    private val commonResultTV by lazy { findViewById<TextView>(R.id.commonResultTV) }
    private val mantrasTV by lazy { findViewById<TextView>(R.id.mantrasTextView) }
    private val mantrasBtn by lazy { findViewById<Button>(R.id.mantrasBtn) }
    private val scrollContainer by lazy { findViewById<ScrollView>(R.id.scrollContainer) }

    override fun onCreate(savedInstanceState: Bundle?) {
        (applicationContext as OtusApplication).appComponent.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.uiState.collect { state ->
                    when (state) {
                        is UIState.CurrentTaskState -> {
                            printCurrentTaskState(state.result)
                        }
                        is UIState.MantrasState -> {
                            printMantrasState(state.message)
                        }
                    }
                }
            }
        }

        initializeListeners()

    }

    private fun initializeListeners() {
        commonResultTV.setOnClickListener {
            sendEvent(UIEvent.NEXT_TASK)
        }

        mantrasBtn.setOnClickListener {
            sendEvent(UIEvent.NEXT_MANTRAS)
        }
    }

    private fun sendEvent(event: UIEvent) {
        viewModel.onEvent(event)
    }

    private fun printCurrentTaskState(result: String) {
        mantrasTV.gone()
        commonResultTV.text = result
        scrollContainer.visible()
    }

    private fun printMantrasState(message: String) {
        scrollContainer.gone()
        mantrasTV.text = message
        mantrasTV.visible()
    }
}

private fun View.visible() {
    visibility = View.VISIBLE
}

private fun View.gone() {
    visibility = View.GONE
}