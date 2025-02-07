package com.example.moderncalculator.viewmodel

import CalculatorBrain
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class CalculatorViewModel : ViewModel() {
    private val brain = CalculatorBrain() // Instance of the CalculatorBrain for logic handling

    // Initialize states correctly
    private val _displayText = MutableStateFlow("0") // Internal state for display text
    val displayText: StateFlow<String> = _displayText // Observable state for display text

    private val _provisionalResult = MutableStateFlow("") // New state for provisional result
    val provisionalResult: StateFlow<String> = _provisionalResult // Observable state for provisional result

    // New state variable to track if the result has been shown
    private var isResultShown = false

    // Handles backspace input
    fun processBackspace() {
        viewModelScope.launch {
            if (_displayText.value.isNotEmpty()) {
                _displayText.value = _displayText.value.dropLast(1) // Remove the last character
            }
            // If the display text is empty, set it to "0"
            if (_displayText.value.isEmpty()) {
                _displayText.value = "0"
            }
        }
    }

    // Processes user input and updates the state accordingly
    fun processInput(input: String) {
        viewModelScope.launch {
            when (input) {
                "=" -> {
                    // Calculate the result using CalculatorBrain
                    val result = brain.processInput(input)
                    if (result == "Errore") {
                        _displayText.value = ""
                        _provisionalResult.value = "Errore" // Show error without '='
                    } else {
                        _displayText.value = "" // Reset the expression
                        _provisionalResult.value = result.toString() // Show result without '='
                    }
                    isResultShown = true // Set state to result shown
                }
                "C" -> {
                    brain.processInput("C") // Clear the CalculatorBrain
                    _displayText.value = ""
                    _provisionalResult.value = "0"
                    isResultShown = false // Reset the state
                }
                else -> {
                    // Handle numeric or operator input
                    if (isResultShown && input.toDoubleOrNull() != null) {
                        // If the result is shown and the input is a number, start a new expression
                        brain.processInput("C") // Reset the CalculatorBrain
                        _displayText.value = ""
                        _provisionalResult.value = "=0"
                        isResultShown = false
                    }
                    // Process the input and update the expression
                    val expression = brain.processInput(input)
                    _displayText.value = expression
                    // Get the provisional result and update the state
                    val provisional = brain.getProvisionalResult()
                    _provisionalResult.value = if (provisional.isNotEmpty()) "= $provisional" else "0"
                    isResultShown = false // Continue building the expression
                }
            }
        }
    }
}