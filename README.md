Ex1Main - Basic Calculator Program with Base Conversion

This program is part of the Intro2CS, Ex1 exercise. It implements a basic interactive calculator that allows users to perform arithmetic operations (addition and multiplication) on two string representations of numbers. The program supports base conversions for the input numbers, allowing the user to work with different numeric bases (from 2 to 16).

Features

Input: The program prompts the user to input two numbers as strings (in any base).
Validation: It validates whether the input strings are valid numbers and supports conversion between different bases (from 2 to 16).
Operations: The program performs the following:
Addition (+)
Multiplication (*)
Base Conversion: The user can specify the base for the output (between 2 and 16).
Max Calculation: After performing the operations, the program calculates the maximum number among the input values and results.
How It Works

User Input:
The program repeatedly prompts the user for two numbers (num1 and num2), one at a time.
For each number, the program checks if it is a valid number (using the Ex1.isNumber() method).
The user can type "quit" at any time to exit the program.
Base Input:
After the two numbers are entered, the user is prompted to specify a base (from 2 to 16) for the arithmetic operations.
The program performs addition and multiplication of the numbers in the specified base.
Results:
For each pair of valid numbers, the program:
Performs addition and multiplication and displays the results.
Determines and displays the largest number from the inputs and results.
Exit:
The program will terminate when the user inputs "quit" for either num1 or num2.
Methods Used

Ex1.isNumber(String a): Checks if the given string can be converted to a number.
Ex1.number2Int(String num): Converts the string representation of a number into an integer.
Ex1.calculateNumbers(String num1, String num2, int base, char operator): Performs the specified arithmetic operation (+ or *) on num1 and num2 in the given base using Ex1.number2Int(String num) and int2Number(int num, int base)
Ex1.maxIndex(String[] arr): Returns the index of the maximum value from an array of strings representing numbers.
