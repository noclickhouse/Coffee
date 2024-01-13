package com.example.coffee.presentation.common

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.coffee.R
import com.example.coffee.ui.theme.CoffeeTheme

@Composable
fun DefaultButton(
    @StringRes label: Int,
    enabled: Boolean,
    onClick: () -> Unit
) {
    Button(
        onClick = onClick,
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        enabled = enabled
    ) {
        Text(
            text = stringResource(id = label),
            modifier = Modifier.padding(vertical = 8.dp),
            color = MaterialTheme.colorScheme.scrim,
            style = MaterialTheme.typography.bodyLarge
        )
    }
}

@Preview
@Composable
fun DefaultButtonPreview() {
    CoffeeTheme {
        DefaultButton(
            label = R.string.sign_up,
            enabled = true,
            onClick = { }
        )
    }
}