package com.example.apply_digital_test.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.applydigitaltest.ui.navigation.Routes
import com.applydigitaltest.ui.navigation.mainScreen

@Composable
fun MainNavigation(
    navController: NavHostController = rememberNavController(),
) {
    NavHost(navController = navController, startDestination = Routes.MainScreen.route ) {
        mainScreen()
    }
}