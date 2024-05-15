package com.example.apply_digital_test

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.apply_digital_test.navigation.MainNavigation
import com.example.apply_digital_test.ui.theme.Apply_Digital_TestTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Apply_Digital_TestTheme {
                MainNavigation()
            }
        }
    }
}
