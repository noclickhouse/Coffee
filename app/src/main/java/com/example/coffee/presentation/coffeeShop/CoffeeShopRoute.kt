package com.example.coffee.presentation.coffeeShop

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.navigation.NavHostController
import com.example.coffee.R
import com.example.coffee.presentation.CoffeeDestinations
import com.example.coffee.presentation.ErrorDialogState
import com.example.coffee.presentation.OrderRouteArguments
import com.example.coffee.presentation.common.EmptyScreen
import com.example.coffee.presentation.common.LoadingScreen
import com.example.coffee.presentation.model.CoffeeView
import com.example.coffee.presentation.registration.ErrorDialog

@Composable
fun CoffeeShopRoute(
    navController: NavHostController,
    coffeeShopViewModel: CoffeeShopViewModel,
    coffeeShopId: Int
) {
    coffeeShopViewModel.getMenu(coffeeShopId)

    CoffeeShopRoute(navController, coffeeShopViewModel) {
        navController.navigateUp()
    }
}

@Composable
fun CoffeeShopRoute(
    navController: NavHostController,
    coffeeShopViewModel: CoffeeShopViewModel,
    onBack: () -> Unit
) {
    val uiState by coffeeShopViewModel.state.collectAsState()
    val dialogState by coffeeShopViewModel.dialogState.collectAsState()

    CoffeeShopRoute(
        state = uiState,
        dialogState = dialogState,
        updateCoffeeCount = { id, newCount -> coffeeShopViewModel.updateCount(id, newCount) },
        onProceedToCheckout = {
            val coffeeListJson = coffeeShopViewModel.convertToJson(it)
            navController.navigate("${CoffeeDestinations.OrderRoute.route}?${OrderRouteArguments.COFFEE_LIST}={${OrderRouteArguments.COFFEE_LIST}}".replace("{${OrderRouteArguments.COFFEE_LIST}}", coffeeListJson))
        },
        onBack = onBack,
        onDismiss = { coffeeShopViewModel.closeDialog() }
    )
}

@Composable
fun CoffeeShopRoute(
    state: CoffeeShopScreenState,
    dialogState: ErrorDialogState,
    updateCoffeeCount: (Int, Int) -> Unit,
    onProceedToCheckout: (List<CoffeeView>) -> Unit,
    onBack: () -> Unit,
    onDismiss: () -> Unit
) {
    when (state) {
        CoffeeShopScreenState.Loading -> LoadingScreen()
        CoffeeShopScreenState.Empty -> EmptyScreen(R.string.menu_is_empty)
        is CoffeeShopScreenState.CoffeeMenu -> CoffeeShopScreen(
            coffeeList = state.coffeeList,
            enabled = true,
            onBack = onBack,
            updateCoffeeCount = updateCoffeeCount,
            onProceedToCheckout = onProceedToCheckout
        )
        is CoffeeShopScreenState.SendingData -> CoffeeShopScreen(
            coffeeList = state.coffeeList,
            enabled = false,
            onBack = onBack
        )
    }
    ErrorDialog(
        state = dialogState,
        onDismiss = onDismiss
    )
}