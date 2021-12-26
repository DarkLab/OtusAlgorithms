package com.darklab.android.otusalgorithms

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.darklab.android.otusalgorithms.tasks.TaskMantras
import com.darklab.android.otusalgorithms.test.Tester
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
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

    private val defaultString = resourcesProvider.getString(R.string.default_result)
    private val stringProcessing = resourcesProvider.getString(R.string.processing)

    private val lastJob: Job? = null

    private val _uiState = MutableStateFlow<UIState>(UIState.CurrentTaskState(defaultString))
    val uiState: StateFlow<UIState> = _uiState.asStateFlow()

    fun onEvent(event: UIEvent) {
        if (lastJob?.isActive == true) return

        viewModelScope.launch {
            when (event) {
                UIEvent.NEXT_TASK -> performCurrentTask()
                UIEvent.NEXT_MANTRAS -> newMantras()
            }
        }
    }

    private suspend fun performCurrentTask() {
        _uiState.emit(UIState.CurrentTaskState(stringProcessing))

        delay(START_DELAY)
        withContext(Dispatchers.Default) {
            Tester(
                taskRepository.requiredTask,
                assetsProvider
            ).runTests()
        }.also {
        _uiState.emit(UIState.CurrentTaskState(it))
    }

    private suspend fun newMantras() {
        _uiState.emit(UIState.CurrentTaskState(stringProcessing))

        delay(START_DELAY)
        withContext(Dispatchers.Default) {
            Tester(
                TaskMantras(),
                assetsProvider
            ).runTests()
        }.also {
        _uiState.emit(UIState.MantrasState(it))
    }
}