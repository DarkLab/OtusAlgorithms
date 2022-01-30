package com.darklab.android.otusalgorithms

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.darklab.android.otusalgorithms.databinding.ActivityMainBinding
import com.darklab.android.otusalgorithms.di.MainViewComponent
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

class MainActivity : AppCompatActivity() {
    private lateinit var mainViewComponent: MainViewComponent

    @Inject
    lateinit var viewModelFactory: GenericViewModelFactory<CurrentTaskViewModel>

    private val viewModel by lazy {
        ViewModelProvider(this, viewModelFactory)[CurrentTaskViewModel::class.java]
    }

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        mainViewComponent = (applicationContext as OtusApplication)
            .appComponent
            .mainViewComponent()
            .create()
        mainViewComponent.inject(this)

        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

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
        binding.performTaskBtn.setOnClickListener {
            sendEvent(UIEvent.NEXT_TASK)
        }

        binding.mantrasBtn.setOnClickListener {
            sendEvent(UIEvent.NEXT_MANTRAS)
        }
    }

    private fun sendEvent(event: UIEvent) {
        viewModel.onEvent(event)
    }

    private fun printCurrentTaskState(result: String) {
        binding.mantrasTextView.gone()
        binding.commonResultTV.text = result
        binding.scrollContainer.visible()
    }

    private fun printMantrasState(message: String) {
        binding.scrollContainer.gone()
        binding.mantrasTextView.text = message
        binding.mantrasTextView.visible()
    }
}

private fun View.visible() {
    visibility = View.VISIBLE
}

private fun View.gone() {
    visibility = View.GONE
}