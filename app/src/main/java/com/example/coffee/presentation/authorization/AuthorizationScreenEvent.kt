package com.example.coffee.presentation.authorization

import com.example.coffee.core.platform.UiEvent
import com.example.coffee.presentation.model.AuthView

sealed class AuthorizationScreenEvent : UiEvent {
    data object Open : AuthorizationScreenEvent()

    data class UpdateEmail(
        val email: String,
        val isValid: Boolean
    ) : AuthorizationScreenEvent()

    data class UpdatePassword(
        val password: String,
        val isValid: Boolean
    ) : AuthorizationScreenEvent()

    data class SendData(val auth: AuthView) : AuthorizationScreenEvent()
    data object Close : AuthorizationScreenEvent()
}