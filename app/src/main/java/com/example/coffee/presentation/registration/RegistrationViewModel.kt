package com.example.coffee.presentation.registration

import androidx.lifecycle.viewModelScope
import com.example.coffee.core.platform.BaseViewModel
import com.example.coffee.core.platform.Either
import com.example.coffee.core.platform.Reducer
import com.example.coffee.domain.token.TokenRepository
import com.example.coffee.domain.user.UserRepository
import com.example.coffee.presentation.model.UserView
import com.example.coffee.presentation.model.toModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RegistrationViewModel @Inject constructor(
    private val userRepository: UserRepository,
    private val tokenRepository: TokenRepository,
    private val registrationValidator: RegistrationValidator
) : BaseViewModel<RegistrationScreenState, RegistrationScreenEvent>() {
    private val reducer = RegistrationReducer(RegistrationScreenState.Loading)

    override val state: StateFlow<RegistrationScreenState>
        get() = reducer.state

    init {
        loadScreen()
    }

    fun updateEmail(email: String) {
        val isValid = registrationValidator.validateEmail(email)
        sendEvent(RegistrationScreenEvent.UpdateEmail(email, isValid))
    }

    fun updatePassword(password: String, passwordAgain: String) {
        val isValid = registrationValidator.validatePassword(password)
        sendEvent(RegistrationScreenEvent.UpdatePassword(password, isValid))
        updatePasswordAgain(password, passwordAgain)
    }

    fun updatePasswordAgain(password: String, passwordAgain: String) {
        val isValid = registrationValidator.validatePasswordAgain(password, passwordAgain)
        sendEvent(RegistrationScreenEvent.UpdatePasswordAgain(passwordAgain, isValid))
    }

    fun register(user: UserView) {
        sendEvent(RegistrationScreenEvent.SendData(user))
        viewModelScope.launch {
            when (val token = userRepository.register(user.toModel())) {
                is Either.Left -> {
                    sendEvent(RegistrationScreenEvent.Open)
                    openDialog(token.failure)
                }
                is Either.Right -> {
                    tokenRepository.saveToken(token.data)
                    sendEvent(RegistrationScreenEvent.Close)
                }
            }
        }
    }

    private fun loadScreen() = sendEvent(RegistrationScreenEvent.Open)

    private fun sendEvent(event: RegistrationScreenEvent) = reducer.sendEvent(event)

    private class RegistrationReducer(initialValue: RegistrationScreenState) :
        Reducer<RegistrationScreenState, RegistrationScreenEvent>(initialValue) {
        override fun reduce(oldState: RegistrationScreenState, event: RegistrationScreenEvent) {
            when (event) {
                RegistrationScreenEvent.Open -> setState(
                    RegistrationScreenState.InputtingData(UserView.empty())
                )
                is RegistrationScreenEvent.UpdateEmail -> setState(
                    when (oldState) {
                        is RegistrationScreenState.InputtingData ->
                            oldState.copy(oldState.user.copy(email = event.email, isEmailNotValid = !event.isValid))
                        else -> RegistrationScreenState.InputtingData(UserView.empty())
                    }
                )
                is RegistrationScreenEvent.UpdatePassword -> setState(
                    when (oldState) {
                        is RegistrationScreenState.InputtingData ->
                            oldState.copy(oldState.user.copy(password = event.password, isPasswordNotValid = !event.isValid))
                        else -> RegistrationScreenState.InputtingData(UserView.empty())
                    }
                )
                is RegistrationScreenEvent.UpdatePasswordAgain -> setState(
                    when (oldState) {
                        is RegistrationScreenState.InputtingData ->
                            oldState.copy(oldState.user.copy(passwordAgain = event.passwordAgain, isPasswordAgainNotValid = !event.isValid))
                        else -> RegistrationScreenState.InputtingData(UserView.empty())
                    }
                )
                is RegistrationScreenEvent.SendData -> setState(
                    RegistrationScreenState.SendingData(event.user)
                )
                RegistrationScreenEvent.Close -> setState(
                    RegistrationScreenState.Navigating
                )
            }
        }
    }
}