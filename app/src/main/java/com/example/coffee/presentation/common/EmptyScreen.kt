package com.example.coffee.presentation.common

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.coffee.R
import com.example.coffee.ui.theme.CoffeeTheme

@Composable
fun EmptyScreen(
    @StringRes displayText: Int
) {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = stringResource(id = displayText),
            color = MaterialTheme.colorScheme.primary,
            style = MaterialTheme.typography.displaySmall
        )
    }
}

@Preview
@Composable
fun EmptyScreenPreview() {
    CoffeeTheme {
        EmptyScreen(R.string.coffee_shops_not_found)
    }
}