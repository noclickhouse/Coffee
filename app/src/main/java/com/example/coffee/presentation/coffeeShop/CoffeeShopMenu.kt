package com.example.coffee.presentation.coffeeShop

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.coffee.presentation.model.CoffeeView
import com.example.coffee.ui.theme.CoffeeTheme

@Composable
fun CoffeeShopMenu(
    coffeeList: List<CoffeeView>,
    enabled: Boolean,
    innerPadding: PaddingValues = PaddingValues(),
    updateCoffeeCount: (Int, Int) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(innerPadding)
    ) {
        LazyVerticalGrid(
            columns = GridCells.Fixed(2)
        ) {
            items(coffeeList) { coffee ->
                CoffeeShopMenuItem(
                    coffee = coffee,
                    enabled = enabled,
                    updateCount = { newCountValue -> updateCoffeeCount(coffee.id, newCountValue) }
                )
            }
        }
    }
}

@Preview
@Composable
fun CoffeeShopMenuPreview() {
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
        CoffeeShopMenu(
            coffeeList = coffeeList,
            enabled = true,
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