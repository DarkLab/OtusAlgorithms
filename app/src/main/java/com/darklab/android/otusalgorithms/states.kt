package com.darklab.android.otusalgorithms

sealed class UIState {
    data class MantrasState(
        val message: String
    ) : UIState()

    data class CurrentTaskState(
        val result: String
    ) : UIState()
}