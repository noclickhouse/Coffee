package com.example.coffee.presentation.registration

import com.example.coffee.core.platform.UiEvent
import com.example.coffee.presentation.model.UserView

sealed class RegistrationScreenEvent : UiEvent {
    data object Open : RegistrationScreenEvent()

    data class UpdateEmail(
        val email: String,
        val isValid: Boolean
    ) : RegistrationScreenEvent()

    data class UpdatePassword(
        val password: String,
        val isValid: Boolean
    ) : RegistrationScreenEvent()

    data class UpdatePasswordAgain(
        val passwordAgain: String,
        val isValid: Boolean
    ) : RegistrationScreenEvent()

    data class SendData(val user: UserView) : RegistrationScreenEvent()
    data object Close : RegistrationScreenEvent()
}