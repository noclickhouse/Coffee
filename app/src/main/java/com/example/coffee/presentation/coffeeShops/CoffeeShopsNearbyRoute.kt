package com.example.coffee.presentation.coffeeShops

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.navigation.NavHostController
import com.example.coffee.R
import com.example.coffee.presentation.CoffeeDestinations
import com.example.coffee.presentation.ErrorDialogState
import com.example.coffee.presentation.common.EmptyScreen
import com.example.coffee.presentation.common.LoadingScreen
import com.example.coffee.presentation.registration.ErrorDialog

@Composable
fun CoffeeShopsNearbyRoute(
    navController: NavHostController,
    coffeeShopsNearbyViewModel: CoffeeShopsNearbyViewModel,
    coordinates: Pair<Double, Double>
) {
    val uiState by coffeeShopsNearbyViewModel.state.collectAsState()
    val dialogState by coffeeShopsNearbyViewModel.dialogState.collectAsState()

    coffeeShopsNearbyViewModel.getCoffeeShops(coordinates)

    CoffeeShopsNearbyRoute(
        state = uiState,
        dialogState = dialogState,
        onClick = { navController.navigate("${CoffeeDestinations.CoffeeShopMenuRoute.route}/${it}") },
        onBack = { navController.navigateUp() },
        onDismiss = { coffeeShopsNearbyViewModel.closeDialog() }
    )
}

@Composable
fun CoffeeShopsNearbyRoute(
    state: CoffeeShopsNearbyScreenState,
    dialogState: ErrorDialogState,
    onClick: (Int) -> Unit,
    onBack: () -> Unit,
    onDismiss: () -> Unit
) {
    when (state) {
        CoffeeShopsNearbyScreenState.Loading -> LoadingScreen()
        CoffeeShopsNearbyScreenState.Empty -> EmptyScreen(R.string.coffee_shops_not_found)
        is CoffeeShopsNearbyScreenState.ShowingCoffeeShops -> CoffeeShopsNearbyScreen(
            coffeeShops = state.coffeeShops,
            onClick = onClick,
            onBack = onBack
        )
    }
    ErrorDialog(
        state = dialogState,
        onDismiss = onDismiss
    )
}