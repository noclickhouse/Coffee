package com.example.coffee.core.platform

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

abstract class Reducer<S : UiState, E : UiEvent>(initialValue: S) {
    private val _state: MutableStateFlow<S> = MutableStateFlow(initialValue)
    val state: StateFlow<S> = _state.asStateFlow()

    fun sendEvent(event: E) {
        reduce(_state.value, event)
    }

    fun setState(state: S) {
        _state.tryEmit(state)
    }

    abstract fun reduce(oldState: S, event: E)
}

interface UiState

interface UiEvent