package com.example.coffee.presentation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController

@Composable
fun CoffeeApp(
    navController: NavHostController,
    coordinates: Pair<Double, Double>
) {
    CoffeeNavGraph(navController, coordinates)
}