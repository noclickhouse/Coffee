package com.example.coffee.presentation.authorization

import androidx.lifecycle.viewModelScope
import com.example.coffee.core.platform.BaseViewModel
import com.example.coffee.core.platform.Either
import com.example.coffee.core.platform.Reducer
import com.example.coffee.domain.token.TokenRepository
import com.example.coffee.domain.user.UserRepository
import com.example.coffee.presentation.model.AuthView
import com.example.coffee.presentation.model.toModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AuthorizationViewModel @Inject constructor(
    private val userRepository: UserRepository,
    private val tokenRepository: TokenRepository,
    private val authorizationValidator: AuthorizationValidator
) : BaseViewModel<AuthorizationScreenState, AuthorizationScreenEvent>() {
    private val reducer = AuthorizationReducer(AuthorizationScreenState.Loading)

    override val state: StateFlow<AuthorizationScreenState>
        get() = reducer.state

    init {
        loadScreen()
    }

    fun updateEmail(email: String) {
        val isValid = authorizationValidator.validateEmail(email)
        sendEvent(AuthorizationScreenEvent.UpdateEmail(email, isValid))
    }

    fun updatePassword(password: String) {
        val isValid = authorizationValidator.validatePassword(password)
        sendEvent(AuthorizationScreenEvent.UpdatePassword(password, isValid))
    }

    fun authorize(auth: AuthView) {
        sendEvent(AuthorizationScreenEvent.SendData(auth))
        viewModelScope.launch {
            when (val token = userRepository.authorize(auth.toModel())) {
                is Either.Left -> {
                    sendEvent(AuthorizationScreenEvent.Open)
                    openDialog(token.failure)
                }
                is Either.Right -> {
                    tokenRepository.saveToken(token.data)
                    sendEvent(AuthorizationScreenEvent.Close)
                }
            }
        }
    }

    private fun loadScreen() {
        sendEvent(AuthorizationScreenEvent.Open)
    }

    private fun sendEvent(event: AuthorizationScreenEvent) {
        reducer.sendEvent(event)
    }

    private class AuthorizationReducer(initialValue: AuthorizationScreenState) :
        Reducer<AuthorizationScreenState, AuthorizationScreenEvent>(initialValue) {
        override fun reduce(oldState: AuthorizationScreenState, event: AuthorizationScreenEvent) {
            when (event) {
                AuthorizationScreenEvent.Open -> setState(
                    AuthorizationScreenState.InputtingData(AuthView.empty())
                )
                is AuthorizationScreenEvent.UpdateEmail -> setState(
                    when (oldState) {
                        is AuthorizationScreenState.InputtingData ->
                            oldState.copy(oldState.auth.copy(email = event.email, isEmailNotValid = !event.isValid))
                        else -> AuthorizationScreenState.InputtingData(AuthView.empty())
                    }
                )
                is AuthorizationScreenEvent.UpdatePassword -> setState(
                    when (oldState) {
                        is AuthorizationScreenState.InputtingData ->
                            oldState.copy(oldState.auth.copy(password = event.password, isPasswordNotValid = !event.isValid))
                        else -> AuthorizationScreenState.InputtingData(AuthView.empty())
                    }
                )
                is AuthorizationScreenEvent.SendData -> setState(
                    AuthorizationScreenState.SendingData(event.auth)
                )
                AuthorizationScreenEvent.Close -> setState(
                    AuthorizationScreenState.Navigation
                )
            }
        }
    }
}