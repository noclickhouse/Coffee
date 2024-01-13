package com.example.coffee.presentation.coffeeShops

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.coffee.presentation.model.CoffeeShopView
import com.example.coffee.presentation.Units
import com.example.coffee.ui.theme.CoffeeTheme

@Composable
fun CoffeeShop(
    coffeeShop: CoffeeShopView,
    onClick: (Int) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 10.dp, vertical = 6.dp)
            .background(
                color = MaterialTheme.colorScheme.scrim,
                shape = RoundedCornerShape(5.dp)
            )
            .clickable { onClick(coffeeShop.id) }
    ) {
        Text(
            text = coffeeShop.name,
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 10.dp, top = 14.dp, end = 10.dp),
            color = MaterialTheme.colorScheme.primary,
            style = MaterialTheme.typography.labelLarge
        )
        Text(
            text = "${coffeeShop.distance} ${coffeeShop.distanceUnit.abbr} от вас",
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 10.dp, top = 6.dp, end = 10.dp, bottom = 14.dp),
            color = MaterialTheme.colorScheme.secondary,
            style = MaterialTheme.typography.labelSmall
        )
    }
}

@Preview
@Composable
fun CoffeeShopPreview() {
    CoffeeTheme {
        val bedoevCoffee = CoffeeShopView(
            id = 1,
            name = "BEDOEV COFFEE",
            distance = 1,
            distanceUnit = Units.Kilometers
        )
        CoffeeShop(
            coffeeShop = bedoevCoffee,
            onClick = { }
        )
    }
}