package com.example.coffee.presentation.common

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import com.example.coffee.R
import com.example.coffee.ui.theme.CoffeeTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopAppBarWithNavButton(
    @StringRes headline: Int,
    onClick: () -> Unit
) {
    TopAppBar(
        title = {
            Text(
                modifier = Modifier.fillMaxWidth(),
                text = stringResource(id = headline),
                color = MaterialTheme.colorScheme.primary,
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.headlineMedium
            )
        },
        navigationIcon = {
            IconButton(onClick = onClick) {
                Icon(
                    painter = painterResource(id = R.drawable.arrow_back),
                    contentDescription = null
                )
            }
        }
    )
}

@Preview
@Composable
fun TopAppBarWithNavButtonPreview() {
    CoffeeTheme {
        Scaffold(
            topBar = {
                TopAppBarWithNavButton(headline = R.string.nearest_coffee_shops) {

                }
            }
        ) { paddingValues ->
            Text(
                modifier = Modifier.padding(paddingValues),
                text = ""
            )
        }
    }
}