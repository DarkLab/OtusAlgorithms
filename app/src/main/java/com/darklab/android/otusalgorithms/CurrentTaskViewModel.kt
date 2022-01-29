package com.darklab.android.otusalgorithms

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.darklab.android.otusalgorithms.tasks.TaskMantras
import com.darklab.android.otusalgorithms.test.Tester
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Singleton

private const val START_DELAY = 1000L

enum class UIEvent {
    NEXT_TASK,
    NEXT_MANTRAS
}

@Singleton
class CurrentTaskViewModel @Inject constructor(
    private val resourcesProvider: ResourcesProvider,
    private val assetsProvider: AssetsProvider,
    private val taskRepository: TaskRepository
) : ViewModel() {

    private val defaultString = "Task Result"
    private val stringProcessing = resourcesProvider.getString(R.string.processing)

    private val _uiState = MutableStateFlow<UIState>(UIState.CurrentTaskState(defaultString))
    val uiState: StateFlow<UIState> = _uiState.asStateFlow()

    fun onEvent(event: UIEvent) {
        when (event) {
            UIEvent.NEXT_TASK -> performCurrentTask()
            UIEvent.NEXT_MANTRAS -> newMantras()
        }
    }

    private fun performCurrentTask() {
        viewModelScope.launch {
            _uiState.emit(UIState.CurrentTaskState(stringProcessing))

            delay(START_DELAY)
            val result = withContext(Dispatchers.Default) {
                Tester(
                    taskRepository.requiredTask,
                    assetsProvider
                ).runTests()
            }

            _uiState.emit(UIState.CurrentTaskState(result))
        }
    }

    private fun newMantras() {
        viewModelScope.launch {
            _uiState.emit(UIState.CurrentTaskState(stringProcessing))

            delay(START_DELAY)
            val result = withContext(Dispatchers.Default) {
                Tester(
                    TaskMantras(),
                    assetsProvider = assetsProvider
                ).runTests()
            }

            _uiState.emit(UIState.MantrasState(result))
        }
    }
}