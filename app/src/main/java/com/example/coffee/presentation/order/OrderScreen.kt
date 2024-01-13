package com.example.coffee.presentation.order

import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import com.example.coffee.R
import com.example.coffee.presentation.common.DefaultButton
import com.example.coffee.presentation.common.TopAppBarWithNavButton
import com.example.coffee.presentation.model.CoffeeView
import com.example.coffee.ui.theme.CoffeeTheme

@Composable
fun OrderScreen(
    coffeeList: List<CoffeeView>,
    paid: Boolean,
    onPay: () -> Unit,
    onBack: () -> Unit,
    updateCoffeeCount: (Int, Int) -> Unit = { _, _ -> }
) {
    Scaffold(
        topBar = {
             TopAppBarWithNavButton(
                 headline = R.string.your_order,
                 onClick = onBack
             )
        },
        bottomBar = {
            BottomAppBar(
                containerColor = MaterialTheme.colorScheme.background,
                contentColor = MaterialTheme.colorScheme.background
            ) {
                DefaultButton(
                    label = when (paid) {
                        true -> R.string.paid
                        false -> R.string.pay
                    },
                    enabled = !paid,
                    onClick = onPay
                )
            }
        },
        containerColor = Color.Transparent,
        contentColor = Color.Transparent
    ) { innerPadding ->
        Order(
            coffeeList = coffeeList,
            paid = paid,
            innerPadding = innerPadding,
            updateCoffeeCount = updateCoffeeCount
        )
    }
}

@Preview
@Composable
fun OrderScreenPreview() {
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
        var paid by remember { mutableStateOf(false) }
        OrderScreen(
            coffeeList = coffeeList,
            paid = paid,
            onPay = { paid = true },
            onBack = { },
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