package com.example.coffee.presentation

import com.example.coffee.core.platform.UiState

sealed interface ErrorDialogState : UiState {
    data object Invisible : ErrorDialogState

    data class Visible(val message: String) : ErrorDialogState
}