package com.example.coffee.presentation.registration

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.coffee.R
import com.example.coffee.presentation.ErrorDialogState

@Composable
fun ErrorDialog(
    state: ErrorDialogState,
    onDismiss: () -> Unit,
    modifier: Modifier = Modifier
) {
    if (state is ErrorDialogState.Visible) {
        AlertDialog(
            title = {
                Text(
                    text = stringResource(id = R.string.error),
                    color = MaterialTheme.colorScheme.primary,
                    style = MaterialTheme.typography.labelSmall
                )
            },
            text = {
                Text(
                    text = state.message,
                    color = MaterialTheme.colorScheme.primary,
                    style = MaterialTheme.typography.labelSmall
                )
            },
            onDismissRequest = onDismiss,
            confirmButton = {
                Button(onClick = onDismiss) {
                    Text(stringResource(id = R.string.ok))
                }
            },
            dismissButton = {
                Button(onClick = onDismiss) {
                    Text(stringResource(id = R.string.dismiss))
                }
            },
            modifier = modifier.padding(16.dp)
        )
    }
}