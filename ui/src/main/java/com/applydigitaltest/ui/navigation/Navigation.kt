package com.applydigitaltest.ui.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.applydigitaltest.ui.mainscreen.MainScreenRoute

fun NavGraphBuilder.mainScreen() {
    composable(Routes.MainScreen.route) {
        MainScreenRoute()
    }
}