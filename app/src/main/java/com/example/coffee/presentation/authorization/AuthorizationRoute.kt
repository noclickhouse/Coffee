package com.example.coffee.presentation.authorization

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.navigation.NavHostController
import com.example.coffee.presentation.CoffeeDestinations
import com.example.coffee.presentation.ErrorDialogState
import com.example.coffee.presentation.common.LoadingScreen
import com.example.coffee.presentation.model.AuthView
import com.example.coffee.presentation.registration.ErrorDialog

@Composable
fun AuthorizationRoute(
    navController: NavHostController,
    authorizationViewModel: AuthorizationViewModel
) {
    val uiState by authorizationViewModel.state.collectAsState()
    val dialogState by authorizationViewModel.dialogState.collectAsState()

    AuthorizationRoute(
        state = uiState,
        dialogState = dialogState,
        onEmailChanged = { authorizationViewModel.updateEmail(it) },
        onPasswordChanged = { authorizationViewModel.updatePassword(it) },
        onAuthorize = { authorizationViewModel.authorize(it) },
        onCoffeeScreenOpen = { navController.navigate(CoffeeDestinations.CoffeeShopsRoute.route) },
        onDismiss = { authorizationViewModel.closeDialog() }
    )
}

@Composable
fun AuthorizationRoute(
    state: AuthorizationScreenState,
    dialogState: ErrorDialogState,
    onEmailChanged: (String) -> Unit,
    onPasswordChanged: (String) -> Unit,
    onAuthorize: (AuthView) -> Unit,
    onCoffeeScreenOpen: () -> Unit,
    onDismiss: () -> Unit
) {
    when (state) {
        AuthorizationScreenState.Loading -> LoadingScreen()
        is AuthorizationScreenState.InputtingData -> AuthorizationScreen(
            auth = state.auth,
            enabled = true,
            onEmailChanged = onEmailChanged,
            onPasswordChanged = onPasswordChanged,
            onAuthorize = { onAuthorize(state.auth) }
        )
        is AuthorizationScreenState.SendingData -> AuthorizationScreen(
            auth = state.auth.copy(
                isEmailNotValid = false,
                isPasswordNotValid = false
            )
        )
        AuthorizationScreenState.Navigation -> onCoffeeScreenOpen()
    }
    ErrorDialog(
        state = dialogState,
        onDismiss = onDismiss
    )
}