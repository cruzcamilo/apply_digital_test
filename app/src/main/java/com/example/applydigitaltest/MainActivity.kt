package com.example.applydigitaltest

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.applydigitaltest.navigation.MainNavigation
import com.example.applydigitaltest.ui.theme.ApplyDigitalTestTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ApplyDigitalTestTheme {
                MainNavigation()
            }
        }
    }
}
