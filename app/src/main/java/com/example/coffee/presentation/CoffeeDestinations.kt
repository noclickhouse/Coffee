package com.example.coffee.presentation

enum class CoffeeDestinations(val route: String) {
    RegistrationRoute("registration"),
    AuthorizationRoute("authorization"),
    CoffeeShopsRoute("coffeeShops"),
    CoffeeShopMenuRoute("coffeeShopMenu"),
    OrderRoute("orderRoute")
}

object CoffeeShopMenuRouteArguments {
    const val ID = "id"
}

object OrderRouteArguments {
    const val COFFEE_LIST = "coffeeList"
}