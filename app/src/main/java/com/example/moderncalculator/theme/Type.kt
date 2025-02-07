package com.example.moderncalculator.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.example.moderncalculator.R

// Define a custom font family using Roboto fonts
val customFontFamily = FontFamily(
    Font(R.font.roboto_regular),  // Regular font style
    Font(R.font.roboto_bold, FontWeight.Bold)  // Bold font style
)

// Custom typography for the app using the defined font family
val Typography = Typography(
    // Style for large body text
    bodyLarge = TextStyle(
        fontFamily = customFontFamily,  // Use the custom font family
        fontWeight = FontWeight.Normal, // Normal font weight
        fontSize = 16.sp,               // Font size
        lineHeight = 24.sp,             // Line height
        letterSpacing = 0.5.sp          // Letter spacing
    ),
    // Style for large title text
    titleLarge = TextStyle(
        fontFamily = customFontFamily,  // Use the custom font family
        fontWeight = FontWeight.Normal, // Normal font weight
        fontSize = 22.sp,               // Font size
        lineHeight = 28.sp,             // Line height
        letterSpacing = 0.sp            // No additional letter spacing
    )
)