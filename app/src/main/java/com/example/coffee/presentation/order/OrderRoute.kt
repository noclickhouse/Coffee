package com.example.coffee.presentation.order

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.navigation.NavHostController
import com.example.coffee.R
import com.example.coffee.presentation.ErrorDialogState
import com.example.coffee.presentation.common.EmptyScreen
import com.example.coffee.presentation.common.LoadingScreen
import com.example.coffee.presentation.model.CoffeeView
import com.example.coffee.presentation.registration.ErrorDialog

@Composable
fun OrderRoute(
    navController: NavHostController,
    orderViewModel: OrderViewModel,
    coffeeList: List<CoffeeView>
) {
    when (coffeeList.isNotEmpty()) {
        true -> orderViewModel.show(coffeeList)
        false -> orderViewModel.showEmpty()
    }
    OrderRoute(orderViewModel) {
        navController.navigateUp()
    }
}

@Composable
fun OrderRoute(
    orderViewModel: OrderViewModel,
    onBack: () -> Unit
) {
    val uiState by orderViewModel.state.collectAsState()
    val dialogState by orderViewModel.dialogState.collectAsState()

    OrderRoute(
        state = uiState,
        dialogState = dialogState,
        onPay = { orderViewModel.pay() },
        onBack = onBack,
        updateCoffeeCount = { id, newCount -> orderViewModel.updateCoffeeCount(id, newCount) },
        onDismiss = { orderViewModel.closeDialog() }
    )
}

@Composable
fun OrderRoute(
    state: OrderScreenState,
    dialogState: ErrorDialogState,
    onPay: () -> Unit,
    onBack: () -> Unit,
    updateCoffeeCount: (Int, Int) -> Unit,
    onDismiss: () -> Unit
) {
    when (state) {
        OrderScreenState.Loading -> LoadingScreen()
        OrderScreenState.Empty -> EmptyScreen(R.string.menu_is_empty)
        is OrderScreenState.Order -> OrderScreen(
            coffeeList = state.order,
            paid = state.paid,
            onPay = onPay,
            onBack = onBack,
            updateCoffeeCount = updateCoffeeCount
        )
    }
    ErrorDialog(
        state = dialogState,
        onDismiss = onDismiss
    )
}