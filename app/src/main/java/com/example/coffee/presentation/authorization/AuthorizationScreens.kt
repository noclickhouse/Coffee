package com.example.coffee.presentation.authorization

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.coffee.R
import com.example.coffee.presentation.common.DefaultButton
import com.example.coffee.presentation.common.DefaultTopAppBar
import com.example.coffee.presentation.common.InputField
import com.example.coffee.presentation.model.AuthView
import com.example.coffee.ui.theme.CoffeeTheme

@Composable
fun AuthorizationScreen(
    auth: AuthView = AuthView.empty(),
    enabled: Boolean = false,
    onEmailChanged: (String) -> Unit = { },
    onPasswordChanged: (String) -> Unit = { },
    onAuthorize: () -> Unit = { }
) {
    Scaffold(
        topBar = {
            DefaultTopAppBar(
                headline = R.string.authorization
            )
        }
    ) { paddingValues: PaddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(horizontal = 18.dp),
            verticalArrangement = Arrangement.Center
        ) {
            InputField(
                labelRes = R.string.email,
                input = auth.email,
                placeholderRes = R.string.email_example,
                warning = R.string.email_warning,
                enabled = enabled,
                isError = auth.isEmailNotValid,
                onValueChange = onEmailChanged
            )
            InputField(
                labelRes = R.string.password,
                input = auth.password,
                placeholderRes = R.string.password_example,
                warning = R.string.password_warning,
                enabled = enabled,
                isError = auth.isPasswordNotValid,
                onValueChange = onPasswordChanged,
                transformation = PasswordVisualTransformation()
            )
            DefaultButton(
                label = R.string.sign_in,
                enabled = enabled && !auth.isEmailNotValid && !auth.isPasswordNotValid,
                onClick = onAuthorize
            )
        }
    }
}

@Preview
@Composable
fun AuthorizationScreenPreview() {
    CoffeeTheme {
        var auth by remember { mutableStateOf(AuthView.empty()) }

        AuthorizationScreen(
            auth = auth,
            enabled = true,
            onEmailChanged = { auth = auth.copy(email = it) },
            onPasswordChanged = { auth = auth.copy(password = it) }
        )
    }
}