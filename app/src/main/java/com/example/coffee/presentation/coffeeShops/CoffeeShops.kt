package com.example.coffee.presentation.coffeeShops

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.coffee.presentation.model.CoffeeShopView
import com.example.coffee.presentation.Units
import com.example.coffee.ui.theme.CoffeeTheme

@Composable
fun CoffeeShops(
    coffeeShops: List<CoffeeShopView>,
    onClick: (Int) -> Unit,
    modifier: Modifier = Modifier
) {
    LazyColumn(
        modifier = modifier
            .fillMaxWidth()
            .padding(top = 9.dp)
    ) {
        items(coffeeShops) { coffeeShop ->
            CoffeeShop(coffeeShop, onClick)
        }
    }
}

@Preview
@Composable
fun CoffeeShopsPreview() {
    CoffeeTheme {
        val coffeeShops = listOf(
            CoffeeShopView(1, "BEDOEV COFFEE", 1, Units.Kilometers),
            CoffeeShopView(2, "Coffee Like", 2, Units.Kilometers),
            CoffeeShopView(3, "EM&DI Coffee and Snacks", 1, Units.Kilometers),
            CoffeeShopView(4, "Коффе есть", 300, Units.Meters),
            CoffeeShopView(5, "BEDOEV COFFEE 2", 3, Units.Kilometers),
        )
        CoffeeShops(
            coffeeShops = coffeeShops,
            onClick = { }
        )
    }
}