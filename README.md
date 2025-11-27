# Binary Arithmetic Operations (Java Swing)

A small desktop GUI application written in Java Swing that performs arithmetic operations on binary numbers. The app supports addition, subtraction, multiplication (Booth's algorithm), and division with quotient and remainder. It focuses on clarity and teaching binary arithmetic as well as providing a handy utility for short binary values.

---

## Features

- Addition and subtraction of binary numbers.
- Multiplication using a Booth algorithm implementation (works for 4-bit inputs internally in the Booth class example).
- Division with quotient and remainder returned in binary form.
- Simple Swing-based GUI with input validation and a visually separated layout.

---

## Project structure

```
ArithmeticOperationsGUI.java    # Main Swing application and Booth multiplication class (inner class)
README.md                       # This file
```

If you split the code into multiple files later (for example moving Booth into its own class), update the project structure accordingly.

---

## Requirements

- Java JDK 8 or later
- A Java IDE (IntelliJ IDEA, Eclipse, NetBeans) or command-line `javac` / `java`

---

## How to build and run

### From an IDE

1. Create a new Java project and add `ArithmeticOperationsGUI.java` to the project src folder.
2. Build and run the project. The Swing window will appear.

### From the command line

1. Save `ArithmeticOperationsGUI.java` to a folder.
2. Open a terminal in that folder and compile:

```bash
javac ArithmeticOperationsGUI.java
```

3. Run the program:

```bash
java ArithmeticOperationsGUI
```

---

## Usage

1. Start the application.
2. Enter two binary numbers into the `Binary Number 1` and `Binary Number 2` fields (only `0` and `1` characters are meaningful).
3. Choose an operation from the dropdown: `Addition`, `Subtraction`, `Multiplication`, or `Division`.
4. Click **Select Operation** or **Calculate** to compute and display the result in the `Result (Binary)` field.

**Notes:**

- Division returns the quotient and remainder in binary (formatted like: `Quotient: 101 Remainder: 10`).
- The current Booth implementation in the inner `Booth` class is a teaching/example implementation designed for small (4-bit) signed integers and ultimately returns an 8-bit result masked by `0xFF`. If you plan to use much larger binary numbers, consider replacing that implementation with `BigInteger` arithmetic or an extended Booth implementation that supports arbitrary widths.

---

## Input validation

- The GUI should validate inputs to contain only `0` and `1`. If not validated yet in your version, add checks before parsing.
- Division by zero is handled by throwing an `ArithmeticException` in the code; consider catching and displaying a friendly message.

---

## Improvements & TODOs

- Support arbitrarily long binary numbers (use `java.math.BigInteger`).
- Move `Booth` into a separate class file and expand it to support larger bit widths or replace with a generic multiplication implementation.
- Add clearer input validation and user-friendly error dialogs (e.g., `JOptionPane.showMessageDialog`).
- Add unit tests for arithmetic functions.
- Improve the GUI layout and accessibility.

## Contributing

Feel free to open issues or pull requests. When making changes, include tests (if possible) and keep GUI and logic separated (MVC pattern) for maintainability.

---

## Contact

Created by: MD SAKIB HASAN EMON

You can customize this README if you want different wording, a project badge, screenshots, or CI instructions. Tell me what to add and I will update it.
