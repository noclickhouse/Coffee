package com.example.coffee.core.platform

import androidx.lifecycle.ViewModel
import com.example.coffee.presentation.ErrorDialogEvent
import com.example.coffee.presentation.ErrorDialogState
import com.example.coffee.core.failure.Failure
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.StateFlow

abstract class BaseViewModel<S: UiState, in E: UiEvent> : ViewModel() {
    private val reducer = FailureReducer(ErrorDialogState.Invisible)

    val dialogState: StateFlow<ErrorDialogState>
        get() = reducer.state

    abstract val state: Flow<S>

    fun openDialog(failure: Failure) = sendEvent(ErrorDialogEvent.Open(failure))

    fun closeDialog() = sendEvent(ErrorDialogEvent.Close)

    private fun sendEvent(event: ErrorDialogEvent) = reducer.sendEvent(event)

    private class FailureReducer(initialValue: ErrorDialogState) :
        Reducer<ErrorDialogState, ErrorDialogEvent>(initialValue) {
            override fun reduce(oldState: ErrorDialogState, event: ErrorDialogEvent) {
                when (event) {
                    is ErrorDialogEvent.Open -> setState(
                        ErrorDialogState.Visible(
                            handleFailure(event.failure)
                        )
                    )
                    ErrorDialogEvent.Close -> setState(ErrorDialogState.Invisible)
                }
            }

            private fun handleFailure(failure: Failure): String =
                when (failure) {
                    Failure.ConnectionError -> "Connection Error"
                    is Failure.FeatureError -> failure.getMessage()
                    is Failure.ServerError -> failure.message
                    Failure.StorageError -> "Storage Error"
                }
    }
}