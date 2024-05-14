package com.applydigitaltest.ui.navigation

sealed class Routes(val route: String) {
    data object MainScreen: Routes("main_screen")
}