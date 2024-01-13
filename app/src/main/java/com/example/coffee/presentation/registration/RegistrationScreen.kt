package com.example.coffee.presentation.registration

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
import com.example.coffee.presentation.common.InputField
import com.example.coffee.R
import com.example.coffee.presentation.common.DefaultButton
import com.example.coffee.presentation.common.DefaultTopAppBar
import com.example.coffee.presentation.model.UserView
import com.example.coffee.ui.theme.CoffeeTheme

@Composable
fun RegistrationScreen(
    user: UserView = UserView.empty(),
    enabled: Boolean = false,
    onEmailChanged: (String) -> Unit = { },
    onPasswordChanged: (String) -> Unit = { },
    onPasswordAgainChanged: (String) -> Unit = { },
    onRegister: () -> Unit = { }
) {
    Scaffold(
        topBar = {
            DefaultTopAppBar(
                headline = R.string.registration
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
                input = user.email,
                placeholderRes = R.string.email_example,
                warning = R.string.email_warning,
                enabled = enabled,
                isError = user.isEmailNotValid,
                onValueChange = onEmailChanged
            )
            InputField(
                labelRes = R.string.password,
                input = user.password,
                placeholderRes = R.string.password_example,
                warning = R.string.password_warning,
                enabled = enabled,
                isError = user.isPasswordNotValid,
                onValueChange = onPasswordChanged,
                transformation = PasswordVisualTransformation()
            )
            InputField(
                labelRes = R.string.password_again,
                input = user.passwordAgain,
                placeholderRes = R.string.password_example,
                warning = R.string.password_again_warning,
                enabled = enabled,
                isError = user.isPasswordAgainNotValid,
                onValueChange = onPasswordAgainChanged,
                transformation = PasswordVisualTransformation()
            )
            DefaultButton(
                label = R.string.sign_up,
                enabled = enabled && !user.isEmailNotValid && !user.isPasswordNotValid && !user.isPasswordAgainNotValid,
                onClick = onRegister
            )
        }
    }
}

@Preview
@Composable
fun RegistrationScreenPreview() {
    CoffeeTheme {
        var user by remember { mutableStateOf(UserView.empty()) }

        RegistrationScreen(
            user = user,
            enabled = true,
            onEmailChanged = { user = user.copy(email = it) },
            onPasswordChanged = { user = user.copy(password = it) },
            onPasswordAgainChanged = { user = user.copy(passwordAgain = it) }
        )
    }
}