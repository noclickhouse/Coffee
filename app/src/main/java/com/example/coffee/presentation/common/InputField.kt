package com.example.coffee.presentation.common

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.coffee.R
import com.example.coffee.core.extension.empty
import com.example.coffee.ui.theme.CoffeeTheme
import com.example.coffee.ui.theme.Typography

@Composable
fun InputField(
    @StringRes labelRes: Int,
    input: String,
    @StringRes placeholderRes: Int,
    @StringRes warning: Int,
    enabled: Boolean,
    isError: Boolean,
    onValueChange: (String) -> Unit,
    transformation: VisualTransformation = VisualTransformation.None
) {
    Column(
        modifier = Modifier
            .padding(vertical = 12.dp)
    ) {
        Text(
            modifier = Modifier.padding(bottom = 8.dp),
            text = stringResource(id = labelRes),
            color = MaterialTheme.colorScheme.primary,
            style = MaterialTheme.typography.labelMedium
        )
        OutlinedTextField(
            value = input,
            onValueChange = onValueChange,
            modifier = Modifier.fillMaxWidth(),
            enabled = enabled,
            textStyle = Typography.labelLarge,
            placeholder = {
                Text(
                    text = stringResource(id = placeholderRes)
                )
            },
            supportingText = {
                Text(
                    text = when (isError) {
                        true -> stringResource(id = warning)
                        false -> String.empty()
                    },
                    style = MaterialTheme.typography.labelMedium
                )
            },
            visualTransformation = transformation,
            shape = CircleShape,
            colors = OutlinedTextFieldDefaults.colors(
                focusedTextColor = MaterialTheme.colorScheme.primary,
                unfocusedTextColor = MaterialTheme.colorScheme.secondary,
                disabledTextColor = MaterialTheme.colorScheme.scrim,
                cursorColor = MaterialTheme.colorScheme.primary,
                focusedBorderColor = MaterialTheme.colorScheme.primary,
                unfocusedBorderColor = MaterialTheme.colorScheme.secondary,
                disabledBorderColor = MaterialTheme.colorScheme.scrim,
                focusedPlaceholderColor = MaterialTheme.colorScheme.primary,
                unfocusedPlaceholderColor = MaterialTheme.colorScheme.secondary,
                disabledPlaceholderColor = MaterialTheme.colorScheme.scrim,
                focusedSupportingTextColor = MaterialTheme.colorScheme.primary,
                unfocusedSupportingTextColor = MaterialTheme.colorScheme.secondary,
                disabledSupportingTextColor = MaterialTheme.colorScheme.scrim
            )
        )
    }
}

@Preview
@Composable
fun InputFieldPreview() {
    CoffeeTheme {
        var email by remember { mutableStateOf(String.empty()) }

        InputField(
            labelRes = R.string.email,
            input = email,
            placeholderRes = R.string.email_example,
            warning = R.string.email_warning,
            enabled = true,
            isError = true,
            onValueChange = { email = it }
        )
    }
}