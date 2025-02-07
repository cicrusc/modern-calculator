package com.example.moderncalculator.ui.components

// Compose UI imports
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

// Material Design imports
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text

// Custom theme imports
import com.example.moderncalculator.theme.LightBlue
import com.example.moderncalculator.theme.LightGray
import com.example.moderncalculator.theme.LightPink
import com.example.moderncalculator.theme.LightWhite

@Composable
fun CalculatorButton(
    symbol: String,
    onClick: () -> Unit,
    isOperator: Boolean = false,
    modifier: Modifier = Modifier
) {
    // Custom colors for different button types
    val buttonColor = when {
        symbol == "AC" -> LightGray // Green color for the "AC" button
        symbol == "←" -> LightGray
        symbol == "=" -> LightPink  // Light violet color for the "=" button
        isOperator -> LightBlue     // Blue color for operators
        else -> LightWhite          // White color for numbers
    }

    // Text color based on button type
    val textColor = when {
        symbol == "AC" || symbol == "=" -> Color.Black  // Black text for "AC" and "="
        isOperator -> Color.White                       // White text for operators
        else -> Color.Black                             // Black text for numbers
    }

    Button(
        onClick = onClick,
        modifier = modifier
            .aspectRatio(1f)  // Set a 1:1 aspect ratio for square buttons
            .padding(2.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = buttonColor // Apply custom button color
        ),
        shape = RoundedCornerShape(5.dp) // Square button (no rounded corners)
    ) {
        Text(
            text = symbol,
            style = MaterialTheme.typography.headlineMedium,
            color = textColor // Text color
        )
    }
}