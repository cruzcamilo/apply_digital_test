package com.applydigitaltest.ui.navigation

sealed class Routes(val route: String) {
    data object MainScreen: Routes("main_screen")
    data object DetailScreen: Routes("detail_screen?url={url}") {
        fun createRoute(url: String) = "detail_screen?url=$url"
        const val argument = "url"
    }
}
