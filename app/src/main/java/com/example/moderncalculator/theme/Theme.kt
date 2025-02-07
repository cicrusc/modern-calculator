package com.example.moderncalculator.theme

// Android and Compose imports
import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext

// Light Color Scheme
private val LightColorScheme = lightColorScheme(
    primary = Color(0xFFA6D1D7),     // Light blue for operators
    secondary = Color(0xFF465256),   // Dark gray for secondary elements
    background = Color(0xFFFFFFFF),  // White background
    surface = Color.White,           // White surface
    onPrimary = Color.White,         // White text on primary elements
    onSecondary = Color.White,       // White text on secondary elements
    onSurface = Color.Black          // Black text on surface
)

// Custom colors for buttons and UI elements
val LightBlue = Color(0xFFA6D1D7)    // Light blue for operators
val LightPink = Color(0xFFF8BBD0)    // Light pink for special buttons
val LightGreen = Color(0xFF80CBC4)   // Light green for equals button
val LightWhite = Color(0xFFF8FAFA)   // Light white for number buttons
val LightGray = Color(0xFFE0F2F1)    // Light gray for "AC" button
val White = Color(0xFFFFFFFF)        // Pure white
val DarkGray = Color(0xFF607D8B)     // Dark gray for secondary elements

// Dark Color Scheme
private val DarkColorScheme = darkColorScheme(
    primary = Color(0xFF303334),     // Dark gray for operators
    secondary = Color(0xFF8CA4AF),   // Light gray for secondary elements
    background = Color(0xFF263238),  // Dark background
    surface = Color(0xFF37474F)      // Dark surface
)

@Composable
fun ModernCalculatorTheme(
    darkTheme: Boolean = isSystemInDarkTheme(), // Check if the system is in dark mode
    dynamicColor: Boolean = true,              // Enable dynamic colors (Android 12+)
    content: @Composable () -> Unit            // Composable content to apply the theme to
) {
    // Determine the color scheme based on system settings and dynamic color support
    val colorScheme = when {
        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
            val context = LocalContext.current
            if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
        }

        darkTheme -> DarkColorScheme
        else -> LightColorScheme
    }

    // Apply the selected color scheme and typography
    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}


