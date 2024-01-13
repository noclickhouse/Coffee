package com.example.coffee.presentation.coffeeShops

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.coffee.presentation.model.CoffeeShopView
import com.example.coffee.R
import com.example.coffee.presentation.Units
import com.example.coffee.presentation.common.DefaultButton
import com.example.coffee.presentation.common.TopAppBarWithNavButton
import com.example.coffee.ui.theme.CoffeeTheme

@Composable
fun CoffeeShopsNearbyScreen(
    coffeeShops: List<CoffeeShopView>,
    onClick: (Int) -> Unit,
    onBack: () -> Unit
) {
    Scaffold(
        topBar = {
            TopAppBarWithNavButton(
                headline = R.string.nearest_coffee_shops,
                onClick = onBack
            )
        },
        bottomBar = {
            BottomAppBar(
                containerColor = MaterialTheme.colorScheme.background,
                contentColor = MaterialTheme.colorScheme.background
            ) {
                DefaultButton(
                    label = R.string.on_map,
                    enabled = true,
                    onClick = { }
                )
            }
        }
    ) { innerPadding ->
        CoffeeShops(
            modifier = Modifier.padding(innerPadding),
            coffeeShops = coffeeShops,
            onClick = onClick
        )
    }
}

@Preview
@Composable
fun CoffeeShopsNearbyScreenPreview() {
    CoffeeTheme {
        val coffeeShops = listOf(
            CoffeeShopView(1, "BEDOEV COFFEE", 1, Units.Kilometers),
            CoffeeShopView(2, "Coffee Like", 2, Units.Kilometers),
            CoffeeShopView(3, "EM&DI Coffee and Snacks", 1, Units.Kilometers),
            CoffeeShopView(4, "Коффе есть", 300, Units.Meters),
            CoffeeShopView(5, "BEDOEV COFFEE 2", 3, Units.Kilometers),
        )
        CoffeeShopsNearbyScreen(coffeeShops, { },{ })
    }
}