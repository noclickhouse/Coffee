package com.example.coffee.presentation

import com.example.coffee.core.failure.Failure
import com.example.coffee.core.platform.UiEvent

sealed class ErrorDialogEvent : UiEvent {
    data object Close : ErrorDialogEvent()
    data class Open(val failure: Failure) : ErrorDialogEvent()
}