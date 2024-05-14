package com.applydigitaltest.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable

@Composable
fun MainScreenRoute() {
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