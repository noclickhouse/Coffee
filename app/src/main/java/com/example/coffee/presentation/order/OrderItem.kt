package com.example.coffee.presentation.order

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.coffee.presentation.coffeeShop.Counter
import com.example.coffee.presentation.model.CoffeeView
import com.example.coffee.ui.theme.CoffeeTheme

@Composable
fun OrderItem(
    coffee: CoffeeView,
    enabled: Boolean,
    updateCount: (Int) -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(6.dp)
            .background(
                shape = RoundedCornerShape(5.dp),
                color = MaterialTheme.colorScheme.scrim
            ),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column {
            Text(
                modifier = Modifier.padding(start = 10.dp, top = 14.dp),
                text = coffee.name,
                color = MaterialTheme.colorScheme.primary,
                style = MaterialTheme.typography.labelLarge
            )
            Text(
               modifier = Modifier.padding(vertical = 3.dp, horizontal = 10.dp),
                text = "${coffee.price} руб",
                color = MaterialTheme.colorScheme.secondary,
                style = MaterialTheme.typography.bodyMedium
            )
        }
        Counter(
            counter = coffee.count,
            increment = { updateCount(coffee.count + 1) },
            enabled = enabled,
            decrement = { if(coffee.count > 0) updateCount.invoke(coffee.count - 1) }
        )
    }
}

@Preview
@Composable
fun OrderItemPreview() {
    CoffeeTheme {        val defaultCoffee = CoffeeView(
        id = 1,
        name = "Эспрессо",
        icon = "https://upload.wikimedia.org/wikipedia/commons/9/9a/Espresso_and_napolitains.jpg",
        price = 200,
        count = 0
    )
        var coffee by remember { mutableStateOf(defaultCoffee) }
        OrderItem(
            coffee = coffee,
            enabled = true,
            updateCount = { newCountValue -> coffee =
                CoffeeView(
                    id = 1,
                    name = "Эспрессо",
                    icon = "https://upload.wikimedia.org/wikipedia/commons/9/9a/Espresso_and_napolitains.jpg",
                    price = 200,
                    count = newCountValue
                )
            }
        )
    }
}