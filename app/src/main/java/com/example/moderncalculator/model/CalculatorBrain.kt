class CalculatorBrain {
    private var currentInput = "0"
    private var expression = ""
    private var operatorPressed = false

    // Processes user input and updates the calculator state
    fun processInput(input: String): String {
        when (input) {
            "C" -> reset() // Reset the calculator
            "+", "-", "×", "÷" -> setOperator(input) // Handle operator input
            "=" -> calculate() // Perform calculation
            else -> updateCurrentInput(input) // Handle numeric or decimal input
        }
        return display() // Return the current display value
    }

    // Resets the calculator to its initial state
    private fun reset() {
        currentInput = "0"
        expression = ""
        operatorPressed = false
    }

    // Updates the current input based on user input
    private fun updateCurrentInput(input: String) {
        operatorPressed = false
        if (input == ".") {
            // Ensure only one decimal point is added
            if (!currentInput.contains(".")) {
                currentInput += "."
            }
        } else {
            // Replace "0" or "Error" with new input, otherwise append
            if (currentInput == "0" || currentInput == "Error") {
                currentInput = input
            } else {
                currentInput += input
            }
        }
    }

    // Sets the operator and updates the expression
    private fun setOperator(op: String) {
        if (operatorPressed) {
            // Replace the last operator with the new one
            if (expression.isNotEmpty()) {
                expression = expression.dropLast(1) + op
            }
        } else {
            // Append the current input and operator to the expression
            expression += currentInput + op
            operatorPressed = true
            currentInput = "0"
        }
    }

    // Performs the calculation and updates the display
    private fun calculate(): String {
        // Append the last number to the expression
        expression += currentInput

        return try {
            // Evaluate the expression and format the result
            val result = evaluateExpression(expression)
            currentInput = formatResult(result)
            expression = ""
            currentInput
        } catch (e: Exception) {
            // Handle errors during calculation
            currentInput = "Error"
            expression = ""
            currentInput
        }
    }

    // Returns the current display value based on the state
    private fun display(): String {
        return if (expression.isEmpty()) {
            currentInput
        } else {
            if (operatorPressed) {
                expression
            } else {
                "$expression$currentInput"
            }
        }
    }

    // Formats the result to remove unnecessary decimal places
    private fun formatResult(result: Double): String {
        return if (result % 1.0 == 0.0) {
            result.toInt().toString()
        } else {
            "%.2f".format(result).trimEnd('0').trimEnd('.')
        }
    }

    /**
     * Evaluates a simple mathematical expression containing +, -, ×, ÷.
     * The algorithm handles operator precedence (multiplication and division first, then addition and subtraction).
     */
    private fun evaluateExpression(expr: String): Double {
        // Replace × and ÷ with * and / for easier evaluation
        val sanitizedExpr = expr.replace("×", "*").replace("÷", "/")

        // Tokenize the expression into numbers and operators
        val tokens = tokenize(sanitizedExpr)

        // Convert the tokens to Reverse Polish Notation (RPN) using the Shunting Yard Algorithm
        val rpn = shuntingYard(tokens)

        // Evaluate the RPN expression and return the result
        return evaluateRPN(rpn)
    }

    // Splits the expression into tokens (numbers and operators)
    private fun tokenize(expr: String): List<String> {
        val tokens = mutableListOf<String>()
        var numberBuffer = ""

        for (char in expr) {
            when {
                char.isDigit() || char == '.' -> {
                    numberBuffer += char
                }
                char in listOf('+', '-', '*', '/') -> {
                    if (numberBuffer.isNotEmpty()) {
                        tokens.add(numberBuffer)
                        numberBuffer = ""
                    }
                    tokens.add(char.toString())
                }
                else -> {
                    throw IllegalArgumentException("Invalid character: $char")
                }
            }
        }
        if (numberBuffer.isNotEmpty()) {
            tokens.add(numberBuffer)
        }
        return tokens
    }

    // Converts infix notation to Reverse Polish Notation (RPN) using the Shunting Yard Algorithm
    private fun shuntingYard(tokens: List<String>): List<String> {
        val output = mutableListOf<String>()
        val operators = mutableListOf<String>()

        val precedence = mapOf(
            "+" to 1,
            "-" to 1,
            "*" to 2,
            "/" to 2
        )

        for (token in tokens) {
            when {
                token.toDoubleOrNull() != null -> {
                    output.add(token)
                }
                token in precedence.keys -> {
                    // Pop higher precedence operators to the output
                    while (operators.isNotEmpty() && operators.last() in precedence.keys &&
                        precedence[operators.last()]!! >= precedence[token]!!
                    ) {
                        output.add(operators.removeAt(operators.size - 1))
                    }
                    operators.add(token)
                }
                else -> {
                    throw IllegalArgumentException("Invalid token: $token")
                }
            }
        }

        // Pop any remaining operators to the output
        while (operators.isNotEmpty()) {
            output.add(operators.removeAt(operators.size - 1))
        }

        return output
    }

    // Evaluates the RPN expression and returns the result
    private fun evaluateRPN(rpn: List<String>): Double {
        val stack = mutableListOf<Double>()

        for (token in rpn) {
            when {
                token.toDoubleOrNull() != null -> {
                    stack.add(token.toDouble())
                }
                token in listOf("+", "-", "*", "/") -> {
                    if (stack.size < 2) {
                        throw IllegalArgumentException("Invalid expression")
                    }
                    val b = stack.removeAt(stack.size - 1)
                    val a = stack.removeAt(stack.size - 1)
                    val result = when (token) {
                        "+" -> a + b
                        "-" -> a - b
                        "*" -> a * b
                        "/" -> {
                            if (b == 0.0) throw ArithmeticException("Division by zero")
                            a / b
                        }
                        else -> throw IllegalArgumentException("Invalid operator: $token")
                    }
                    stack.add(result)
                }
                else -> {
                    throw IllegalArgumentException("Invalid token: $token")
                }
            }
        }

        if (stack.size != 1) {
            throw IllegalArgumentException("Invalid expression")
        }

        return stack[0]
    }

    /**
     * New function to get the provisional result without resetting the state.
     * This is useful for displaying intermediate results while typing.
     */
    fun getProvisionalResult(): String {
        return try {
            val combinedExpression = expression + currentInput
            val result = evaluateExpression(combinedExpression)
            formatResult(result)
        } catch (e: Exception) {
            "" // Return an empty string in case of errors
        }
    }
}