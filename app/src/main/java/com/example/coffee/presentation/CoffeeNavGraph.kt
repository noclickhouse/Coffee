package com.example.coffee.presentation

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.coffee.presentation.authorization.AuthorizationRoute
import com.example.coffee.presentation.authorization.AuthorizationViewModel
import com.example.coffee.presentation.coffeeShop.CoffeeShopRoute
import com.example.coffee.presentation.coffeeShop.CoffeeShopViewModel
import com.example.coffee.presentation.coffeeShops.CoffeeShopsNearbyRoute
import com.example.coffee.presentation.coffeeShops.CoffeeShopsNearbyViewModel
import com.example.coffee.presentation.model.CoffeeListView
import com.example.coffee.presentation.order.OrderRoute
import com.example.coffee.presentation.order.OrderViewModel
import com.example.coffee.presentation.registration.RegistrationRoute
import com.example.coffee.presentation.registration.RegistrationViewModel
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory

@Composable
fun CoffeeNavGraph(
    navController: NavHostController,
    coordinates: Pair<Double, Double>,
    startDestination: CoffeeDestinations = CoffeeDestinations.RegistrationRoute
) {
    NavHost(
        navController = navController,
        startDestination = startDestination.route
    ) {
        composable(CoffeeDestinations.RegistrationRoute.route) {
            val registrationViewModel: RegistrationViewModel = hiltViewModel()

            RegistrationRoute(navController, registrationViewModel)
        }

        composable(CoffeeDestinations.AuthorizationRoute.route) {
            val authorizationViewModel: AuthorizationViewModel = hiltViewModel()

            AuthorizationRoute(navController, authorizationViewModel)
        }

        composable(CoffeeDestinations.CoffeeShopsRoute.route) {
            val coffeeShopsNearbyViewModel: CoffeeShopsNearbyViewModel = hiltViewModel()

            CoffeeShopsNearbyRoute(navController, coffeeShopsNearbyViewModel, coordinates)
        }

        composable("${CoffeeDestinations.CoffeeShopMenuRoute.route}/{${CoffeeShopMenuRouteArguments.ID}}",
            arguments = listOf(navArgument(CoffeeShopMenuRouteArguments.ID) {
                type = NavType.IntType
            })
        ) { navBackStackEntry ->
            val coffeeShopId = navBackStackEntry.arguments?.getInt(CoffeeShopMenuRouteArguments.ID) ?: 0
            val coffeeShopViewModel: CoffeeShopViewModel = hiltViewModel()

            CoffeeShopRoute(navController, coffeeShopViewModel, coffeeShopId)
        }

        composable("${CoffeeDestinations.OrderRoute.route}?${OrderRouteArguments.COFFEE_LIST}={${OrderRouteArguments.COFFEE_LIST}}") { navBackStackEntry ->
            val coffeeListJson = navBackStackEntry.arguments?.getString(OrderRouteArguments.COFFEE_LIST)
            val moshi = Moshi.Builder()
                .add(KotlinJsonAdapterFactory())
                .build()
            val jsonAdapter = moshi.adapter(CoffeeListView::class.java).lenient()
            val coffeeListObject = jsonAdapter.fromJson(coffeeListJson.toString())
            val orderViewModel: OrderViewModel = hiltViewModel()

            OrderRoute(navController, orderViewModel, coffeeListObject?.coffeeList ?: emptyList())
        }
    }
}