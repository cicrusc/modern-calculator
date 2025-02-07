package com.example.moderncalculator.ui.screen

// Android and Compose imports
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp

// ViewModel and state management
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.unit.sp

// Custom components and themes
import com.example.moderncalculator.ui.components.CalculatorButton
import com.example.moderncalculator.viewmodel.CalculatorViewModel

@Composable
fun CalculatorScreen(viewModel: CalculatorViewModel = viewModel()) {
    // Observe values from the ViewModel
    val displayText by viewModel.displayText.collectAsState()
    val provisionalResult by viewModel.provisionalResult.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .padding(16.dp),
        verticalArrangement = Arrangement.SpaceBetween,
        horizontalAlignment = Alignment.End // Align contents to the right
    ) {
        // Display for Expression and Result
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f), // Assign weight to occupy available space
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.End // Align contents to the right
        ) {
            Spacer(modifier = Modifier.height(16.dp)) // Add top margin of 16dp

            // Expression with Dynamic Font Size
            AutoSizeText(
                text = displayText,
                maxLines = 2,
                style = MaterialTheme.typography.displayLarge,
                textAlign = TextAlign.End, // Align text to the right
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(8.dp)) // Space between expression and result

            // Provisional or Final Result
            Text(
                text = provisionalResult,
                color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.5f), // Lighter color
                style = MaterialTheme.typography.bodyLarge.copy(fontSize = 24.sp),
                textAlign = TextAlign.End, // Align text to the right
                modifier = Modifier.fillMaxWidth()
            )
        }

        // Calculator Keypad
        LazyVerticalGrid(
            columns = GridCells.Fixed(4),
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 16.dp)
        ) {
            items(listOf(
                "AC", "←", "%", "÷",    // First row
                "7", "8", "9", "×",      // Second row
                "4", "5", "6", "-",      // Third row
                "1", "2", "3", "+",      // Fourth row
                "↻", "0", ".", "="       // Fifth row
            )) { symbol ->
                CalculatorButton(
                    symbol = symbol,
                    isOperator = symbol in listOf("x̂", "%", "÷", "×", "-", "+", "="),
                    onClick = {
                        when (symbol) {
                            "AC" -> viewModel.processInput("C") // Clear
                            "←" -> viewModel.processBackspace() // Backspace
                            else -> viewModel.processInput(symbol) // Other inputs
                        }
                    }
                )
            }
        }
    }
}

@Composable
fun AutoSizeText(
    text: String,
    maxLines: Int,
    style: androidx.compose.ui.text.TextStyle,
    textAlign: TextAlign = TextAlign.End,
    modifier: Modifier = Modifier
) {
    // Simple implementation of font size reduction based on text length
    val maxFontSize = style.fontSize
    val minFontSize = 16.sp
    val textLength = text.length
    val fontSize = when {
        textLength < 15 -> maxFontSize
        textLength in 15..25 -> maxFontSize * 0.8f
        textLength in 26..35 -> maxFontSize * 0.6f
        else -> minFontSize
    }

    Text(
        text = text,
        maxLines = maxLines,
        style = style.copy(fontSize = fontSize),
        textAlign = textAlign,
        modifier = modifier
    )
}