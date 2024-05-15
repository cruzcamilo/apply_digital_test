package com.example.applydigitaltest.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.applydigitaltest.ui.navigation.Routes
import com.applydigitaltest.ui.navigation.detailScreen
import com.applydigitaltest.ui.navigation.mainScreen

@Composable
fun MainNavigation(
    navController: NavHostController = rememberNavController(),
) {
    NavHost(navController = navController, startDestination = Routes.MainScreen.route ) {
        mainScreen(
            onClickArticle = { url-> navController.navigate(Routes.DetailScreen.createRoute(url)) }
        )
        detailScreen()
    }
}