package com.example.moderncalculator

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.moderncalculator.theme.ModernCalculatorTheme
import com.example.moderncalculator.ui.screen.CalculatorScreen

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Set the content of the activity using Jetpack Compose
        setContent {
            // Apply the app's custom theme (dynamic colors disabled)
            ModernCalculatorTheme(dynamicColor = false) {
                // Display the main CalculatorScreen
                CalculatorScreen()
            }
        }
    }
}