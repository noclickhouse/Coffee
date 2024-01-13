package com.example.coffee.presentation.registration

import com.example.coffee.core.platform.UiState
import com.example.coffee.presentation.model.UserView

sealed interface RegistrationScreenState : UiState {
    data object Loading : RegistrationScreenState
    data class InputtingData(val user: UserView) : RegistrationScreenState
    data class SendingData(val user: UserView) : RegistrationScreenState
    data object Navigating : RegistrationScreenState
}