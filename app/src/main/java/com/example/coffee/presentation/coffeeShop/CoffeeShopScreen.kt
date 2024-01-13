package com.example.coffee.presentation.coffeeShop

import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import com.example.coffee.presentation.model.CoffeeView
import com.example.coffee.R
import com.example.coffee.presentation.common.DefaultButton
import com.example.coffee.presentation.common.TopAppBarWithNavButton
import com.example.coffee.ui.theme.CoffeeTheme

@Composable
fun CoffeeShopScreen(
    coffeeList: List<CoffeeView>,
    enabled: Boolean,
    onBack: () -> Unit,
    updateCoffeeCount: (Int, Int) -> Unit = { _, _ -> },
    onProceedToCheckout: (List<CoffeeView>) -> Unit = { },
) {
    Scaffold(
        topBar = {
            TopAppBarWithNavButton(
                headline = R.string.menu,
                onClick = onBack
            )
        },
        bottomBar = {
            BottomAppBar(
                containerColor = MaterialTheme.colorScheme.background,
                contentColor = MaterialTheme.colorScheme.background
            ) {
                DefaultButton(
                    label = R.string.proceed_to_checkout,
                    enabled = enabled,
                    onClick = { onProceedToCheckout(coffeeList) }
                )
            }
        },
        containerColor = Color.Transparent,
        contentColor = Color.Transparent
    ) { innerPadding ->
        CoffeeShopMenu(
            coffeeList = coffeeList,
            innerPadding = innerPadding,
            enabled = enabled,
            updateCoffeeCount = updateCoffeeCount
        )
    }
}

@Preview
@Composable
fun CoffeeShopScreenPreview() {
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
        CoffeeShopScreen(
            coffeeList = coffeeList,
            updateCoffeeCount = { id, newCountValue ->
                val coffee = coffeeList.find { it.id == id }
                coffee?.let {
                    val index = coffeeList.indexOf(coffee)
                    coffeeList[index] = it.copy(count = newCountValue)
                }
            },
            onProceedToCheckout = { /*TODO*/ },
            enabled = true,
            onBack = { }
        )
    }
}