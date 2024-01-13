package com.example.coffee.presentation.registration

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.navigation.NavHostController
import com.example.coffee.presentation.CoffeeDestinations
import com.example.coffee.presentation.ErrorDialogState
import com.example.coffee.presentation.common.LoadingScreen
import com.example.coffee.presentation.model.UserView

@Composable
fun RegistrationRoute(
    navController: NavHostController,
    registrationViewModel: RegistrationViewModel
) {
    val uiState by registrationViewModel.state.collectAsState()
    val dialogState by registrationViewModel.dialogState.collectAsState()

    RegistrationRoute(
        state = uiState,
        dialogState = dialogState,
        onEmailChanged = { registrationViewModel.updateEmail(it) },
        onPasswordChanged = { req, f -> registrationViewModel.updatePassword(req, f) },
        onPasswordAgainChanged = { req, f -> registrationViewModel.updatePasswordAgain(req, f) },
        onRegister = { registrationViewModel.register(it) },
        onCoffeeScreenOpen = { navController.navigate(CoffeeDestinations.AuthorizationRoute.route) },
        onDismiss = { registrationViewModel.closeDialog() }
    )
}

@Composable
fun RegistrationRoute(
    state: RegistrationScreenState,
    dialogState: ErrorDialogState,
    onEmailChanged: (String) -> Unit,
    onPasswordChanged: (String, String) -> Unit,
    onPasswordAgainChanged: (String, String) -> Unit,
    onRegister: (UserView) -> Unit,
    onCoffeeScreenOpen: () -> Unit,
    onDismiss: () -> Unit
) {
    when (state) {
        RegistrationScreenState.Loading -> LoadingScreen()
        is RegistrationScreenState.InputtingData -> RegistrationScreen(
            user = state.user,
            enabled = true,
            onEmailChanged = onEmailChanged,
            onPasswordChanged = { onPasswordChanged(it, state.user.passwordAgain) },
            onPasswordAgainChanged = { onPasswordAgainChanged(state.user.password, it) },
            onRegister = { onRegister(state.user) }
        )
        is RegistrationScreenState.SendingData -> RegistrationScreen(
            user = state.user.copy(
                isEmailNotValid = false,
                isPasswordNotValid = false,
                isPasswordAgainNotValid = false
            )
        )
        RegistrationScreenState.Navigating -> onCoffeeScreenOpen()
    }
    ErrorDialog(
        state = dialogState,
        onDismiss = onDismiss
    )
}