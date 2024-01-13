package com.example.coffee.presentation.coffeeShop

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.coffee.ui.theme.CoffeeTheme

@Composable
fun Counter(
    counter: Int,
    enabled: Boolean,
    increment: () -> Unit,
    decrement: () -> Unit
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.End
    ) {
        val decrementEnabled = when (counter > 0) {
            true -> true
            false -> false
        }
        Text(
            modifier = Modifier
                .size(20.dp)
                .clip(CircleShape)
                .clickable(enabled = decrementEnabled && enabled, onClick = decrement),
            text = "-",
            color = if (decrementEnabled && enabled) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.secondary,
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.labelSmall
        )
        Text(
            modifier = Modifier.padding(horizontal = 3.dp),
            text = counter.toString(),
            color = MaterialTheme.colorScheme.primary,
            style = MaterialTheme.typography.labelSmall
        )
        Text(
            modifier = Modifier
                .size(20.dp)
                .clip(CircleShape)
                .clickable(enabled = enabled, onClick = increment),
            text = "+",
            color = if (enabled) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.secondary,
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.labelSmall
        )
    }
}

@Preview
@Composable
fun CounterPreview() {
    CoffeeTheme {
        var counter by remember { mutableIntStateOf(0) }
        Counter(
            counter = counter,
            enabled = true,
            increment = { counter++ },
            decrement = { counter-- }
        )
    }
}