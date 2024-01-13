package com.example.coffee.presentation.order

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.coffee.R
import com.example.coffee.presentation.model.CoffeeView
import com.example.coffee.ui.theme.CoffeeTheme

@Composable
fun Order(
    coffeeList: List<CoffeeView>,
    paid: Boolean,
    updateCoffeeCount: (Int, Int) -> Unit,
    innerPadding: PaddingValues = PaddingValues()
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(innerPadding)
    ) {
        LazyColumn(
            modifier = Modifier.padding(horizontal = 6.dp)
        ) {
            items(coffeeList) { coffee ->
                OrderItem(
                    coffee = coffee,
                    enabled = !paid,
                    updateCount = { newCountValue -> updateCoffeeCount.invoke(coffee.id, newCountValue) }
                )
            }
            item {
                if (paid) {
                    Text(
                        modifier = Modifier.padding(vertical = 50.dp),
                        text = stringResource(id = R.string.waiting),
                        color = MaterialTheme.colorScheme.primary,
                        textAlign = TextAlign.Center,
                        style = MaterialTheme.typography.displaySmall
                    )
                }
            }
        }
    }
}

@Preview
@Composable
fun OrderPreview() {
    CoffeeTheme {
        val coffee1 = CoffeeView(
            id = 1,
            name = "Эспрессо",
            icon = "https://upload.wikimedia.org/wikipedia/commons/9/9a/Espresso_and_napolitains.jpg",
            price = 200,
            count = 0
        )
        val coffee2 = CoffeeView(
            id = 2,
            name = "Капучино",
            icon = "https://upload.wikimedia.org/wikipedia/commons/1/16/Classic_Cappuccino.jpg",
            price = 200,
            count = 0
        )
        val coffee3 = CoffeeView(
            id = 3,
            name = "Латте",
            icon = "https://upload.wikimedia.org/wikipedia/commons/c/c6/Latte_art_3.jpg",
            price = 200,
            count = 0
        )
        val coffee4 = CoffeeView(
            id = 4,
            name = "Эспрессо",
            icon = "https://upload.wikimedia.org/wikipedia/commons/9/9a/Espresso_and_napolitains.jpg",
            price = 200,
            count = 0
        )
        val coffee5 = CoffeeView(
            id = 5,
            name = "Капучино",
            icon = "https://upload.wikimedia.org/wikipedia/commons/1/16/Classic_Cappuccino.jpg",
            price = 200,
            count = 0
        )
        val coffee6 = CoffeeView(
            id = 6,
            name = "Латте",
            icon = "https://upload.wikimedia.org/wikipedia/commons/c/c6/Latte_art_3.jpg",
            price = 200,
            count = 0
        )
        val coffeeList = remember { mutableStateListOf(coffee1, coffee2, coffee3, coffee4, coffee5, coffee6) }
        Order(
            coffeeList = coffeeList,
            paid = true,
            updateCoffeeCount = { id, newCountValue ->
                val coffee = coffeeList.find { it.id == id }
                coffee?.let {
                    val index = coffeeList.indexOf(coffee)
                    coffeeList[index] = it.copy(count = newCountValue)
                }
            }
        )
    }
}