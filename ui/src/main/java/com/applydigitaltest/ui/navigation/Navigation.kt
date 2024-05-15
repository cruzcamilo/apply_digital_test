package com.applydigitaltest.ui.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.applydigitaltest.ui.detailscreen.DetailScreenRoute
import com.applydigitaltest.ui.mainscreen.MainScreenRoute

fun NavGraphBuilder.mainScreen(
    onClickArticle: (String) -> Unit
) {
    composable(Routes.MainScreen.route) {
        MainScreenRoute(
            onClickArticle = onClickArticle
        )
    }
}

fun NavGraphBuilder.detailScreen() {
    composable(
        route = Routes.DetailScreen.route,
        arguments = listOf(navArgument(Routes.DetailScreen.argument) {
            type = NavType.StringType
            defaultValue = ""
        })
    ) {
        val url = it.arguments?.getString(Routes.DetailScreen.argument).orEmpty()

        DetailScreenRoute(url)
    }
}