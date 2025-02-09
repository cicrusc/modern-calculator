![Kotlin](https://img.shields.io/badge/Kotlin-7F52FF?style=for-the-badge&logo=kotlin&logoColor=white)
![Jetpack Compose](https://img.shields.io/badge/Jetpack_Compose-4285F4?style=for-the-badge&logo=jetpack-compose&logoColor=white)
![Android](https://img.shields.io/badge/Android-3DDC84?style=for-the-badge&logo=android&logoColor=white)
![MVVM](https://img.shields.io/badge/MVVM-Architecture-FF6F00?style=for-the-badge&logo=android&logoColor=white)
![Material Design](https://img.shields.io/badge/Material_Design-757575?style=for-the-badge&logo=material-design&logoColor=white)
---

# Modern Calculator

A modern, sleek, and user-friendly calculator app for Android, built with **Jetpack Compose** and following **Material Design** guidelines. The app supports basic arithmetic operations, dynamic font sizing, and a clean, intuitive interface.

## Features

- **Basic Arithmetic Operations**: Addition, subtraction, multiplication, and division.
- **Dynamic Font Sizing**: Automatically adjusts the font size of the display to fit long numbers.
- **Provisional Results**: Shows intermediate results while typing.
- **Error Handling**: Displays "Error" for invalid operations (e.g., division by zero).
- **Backspace Functionality**: Allows users to correct mistakes by removing the last digit.
- **Material Design**: Follows Material Design principles for a modern and consistent look.

## Technologies Used

- **Kotlin**: The programming language used for the app.
- **Jetpack Compose**: A modern toolkit for building native Android UI.
- **MVVM Architecture**: Separates the UI logic from the business logic for better maintainability.
- **Material Design**: Provides a cohesive design system for the app's interface.
- **State Management**: Uses `StateFlow` and `MutableStateFlow` to manage and observe UI state.

## Screenshots

| <img src="https://github.com/user-attachments/assets/b7abe870-a1c8-4745-89c6-fa325aa520a2" alt="Calculator set to 0 by default" width="200" /> | <img src="https://github.com/user-attachments/assets/79990686-99ad-4c1f-ab6f-ed5036331771" alt="Example calculation" width="200" /> | <img src="https://github.com/user-attachments/assets/44d30954-af46-4e43-8a6c-7a179d14ebf2" alt="Example expresssion" width="200" /> |
|:---:|:---:|:---:|

## Setup

### 1. Clone the Repository

```bash
git clone https://github.com/your-username/modern-calculator.git
cd modern-calculator
```

### 2. Open the Project in Android Studio

- Open Android Studio and select **Open an Existing Project**.
- Navigate to the cloned repository and select the `modern-calculator` folder.

### 3. Build and Run the App

- Connect an Android device or start an emulator.
- Click the **Run** button in Android Studio (or press `Shift + F10`).
- The app will be installed and launched on your device/emulator.

## Usage

- **Basic Operations**: Use the buttons to perform addition (`+`), subtraction (`-`), multiplication (`×`), and division (`÷`).
- **Equals (`=`)**: Press the `=` button to calculate the result.
- **Clear (`AC`)**: Press the `AC` button to reset the calculator.
- **Backspace (`←`)**: Press the `←` button to remove the last digit.
- **Decimal Point (`.`)**: Use the `.` button to input decimal numbers.

## Files in the Project

- **MainActivity.kt**: The entry point of the app, where the UI is set up using Jetpack Compose.
- **CalculatorViewModel.kt**: Handles the business logic and state management using the MVVM architecture.
- **CalculatorScreen.kt**: The main UI screen, built with Jetpack Compose.
- **CalculatorButton.kt**: A reusable Composable for the calculator buttons.
- **CalculatorBrain.kt**: Contains the logic for parsing and evaluating mathematical expressions.
- **Theme.kt**: Defines the app's color scheme and typography using Material Design.

## Contributing

Feel free to fork the repository and contribute by submitting pull requests. All improvements and bug fixes are welcome!

## License

This project is open-source and available under the [MIT License](LICENSE).

---

```
