package com.example.coffee.presentation.authorization

import com.example.coffee.core.platform.UiState
import com.example.coffee.presentation.model.AuthView

sealed interface AuthorizationScreenState : UiState {
    data object Loading : AuthorizationScreenState
    data class InputtingData(val auth: AuthView) : AuthorizationScreenState
    data class SendingData(val auth: AuthView) : AuthorizationScreenState
    data object Navigation : AuthorizationScreenState
}