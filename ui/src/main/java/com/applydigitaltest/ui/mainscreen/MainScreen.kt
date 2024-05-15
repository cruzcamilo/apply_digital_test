package com.applydigitaltest.ui.mainscreen

import androidx.compose.foundation.layout.Box
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel

@Composable
fun MainScreenRoute(
    mainScreenViewModel: MainScreenViewModel = hiltViewModel()
) {
    MainScreen()
}

@Composable
fun MainScreen() {
    Box {
        Text(text = "Hello from ui!")
    }
}

@Composable
fun MainScreenPreview() {
    MainScreen()
}