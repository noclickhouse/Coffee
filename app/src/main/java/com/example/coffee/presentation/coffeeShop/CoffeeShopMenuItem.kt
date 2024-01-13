package com.example.coffee.presentation.coffeeShop

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.coffee.presentation.model.CoffeeView
import com.example.coffee.ui.theme.CoffeeTheme

@Composable
fun CoffeeShopMenuItem(
    coffee: CoffeeView,
    enabled: Boolean,
    updateCount: (Int) -> Unit
) {
    BoxWithConstraints {
        val width = maxWidth
        ElevatedCard(
            modifier = Modifier
                .padding(8.dp),
            shape = RoundedCornerShape(5.dp)
        ) {
            Column(
                modifier = Modifier.background(MaterialTheme.colorScheme.background)
            ) {
                AsyncImage(
                    modifier = Modifier.size(width),
                    model = coffee.icon,
                    contentDescription = null,
                    contentScale = ContentScale.Crop
                )
                Text(
                    modifier = Modifier.padding(10.dp),
                    text = coffee.name,
                    color = MaterialTheme.colorScheme.secondary,
                    style = MaterialTheme.typography.labelMedium
                )
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(10.dp)
                ) {
                    Text(
                        text = "${coffee.price} руб",
                        color = MaterialTheme.colorScheme.primary,
                        style = MaterialTheme.typography.labelSmall
                    )
                    Counter(
                        counter = coffee.count,
                        increment = { updateCount(coffee.count + 1) },
                        enabled = enabled,
                        decrement = { if(coffee.count > 0) updateCount(coffee.count - 1) }
                    )
                }
            }
        }
    }
}

@Preview
@Composable
fun CoffeeShopMenuItemPreview9() {
    CoffeeTheme {
        val defaultCoffee = CoffeeView(
            id = 1,
            name = "Эспрессо",
            icon = "https://upload.wikimedia.org/wikipedia/commons/9/9a/Espresso_and_napolitains.jpg",
            price = 200,
            count = 0
        )
        var coffee by remember { mutableStateOf(defaultCoffee) }
        CoffeeShopMenuItem(
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