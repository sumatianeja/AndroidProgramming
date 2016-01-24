# Mortgage Calculator Android App

Description
I have implemented a single-activity Android application which is a Mortgage Calculator.

The view will have the following elements:

1) Amount Borrowed : This will be a EditText into which the user will enter the amount to be borrowed as a floating-point value (e.g., “1000.00”).

2) Interest Rate : This will be a SeekBar ranging from 0.0 to 10.0, indicating the annual percentage rate of the interest. The initial value should be set to 5.0. As you move the SeekBar, the displayed interest rate should change.
The interest rate should be represented as decimals, with granularity to one decimal place (e.g., 1.1, 1.2, etc.).

3) Loan Term : This will be a RadioGroup with the choices 7, 15, and 30 representing the number of years of the loan.

4) Taxes and Insurance : This will be a CheckBox that allows the user to select whether taxes and insurance are to be included in the monthly payment. This is a single checkbox.

5) Calculate : This will be a Button that, when pressed, will calculate the user’s monthly payments based on the values entered.

6) Monthly Payment : This will be a TextView that displays the monthly payment.

Calculation
For interest rates other than 0%, the monthly payment can be calculated as: 
Where:

 P = Principal (the amount borrowed)
 J = Monthly interest in decimal form (annual interest rate / 1200)
 N = Number of months of the loan
 T = Monthly taxes and insurance, if selected (0.1% of the amount borrowed)

For interest rates of 0%, the monthly payment is simply:

Other Requirements

    The app shouldn't crash on user input errors, such as if no values are specified and the user clicks the Calculate button.
    Provide support for English and another language using resource files.


![alt tag](https://github.com/sumatianeja/android_programming.git/to/mortgageCalculator1.png)

