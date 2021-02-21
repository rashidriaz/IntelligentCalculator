package calculator.intelligent.dsa;

public class Calculator {
    private static final int arraySize = 10;
    private static final MathematicalExpression[] history = new MathematicalExpression[arraySize];
    private static int counter = 0;
    public static char recentOperator = ' ';

    public static void validateDataAndDisplayResult(MathematicalExpression expression)
            throws InvalidOperatorException {
        double result;
        result = validateDataAndEvaluateResults(expression);
        history[counter] = expression;
        recentOperator = expression.operator;
        counter++;
        IOStream.displayResults(expression, result);
    }
    public static void DisplayResultsUsingPreviousOperator(double firstNumber, double secondNumber)
            throws InvalidOperatorException {
        MathematicalExpression expression = new MathematicalExpression(firstNumber, secondNumber, recentOperator);
        validateDataAndDisplayResult(expression);
    }
    private static double validateDataAndEvaluateResults(MathematicalExpression expression)
            throws InvalidOperatorException {
        double result;
        if (expression.operator == '+') {
            result = expression.firstNumber + expression.secondNumber;
        } else if (expression.operator == '-') {
            result = expression.firstNumber - expression.secondNumber;
        } else if (expression.operator == '*') {
            result = expression.firstNumber * expression.secondNumber;
        } else if (expression.operator == '/') {
            result = expression.firstNumber / expression.secondNumber;
        } else {
            throw new InvalidOperatorException("Invalid Operator");
        }
        return result;
    }

    public static void showPreviousCalculations() throws InvalidOperatorException {
        int pointer = counter - 1;
        if (pointer < 0){
            System.out.println("\n There is nothing to show.\n\n");
            return;
        }
        boolean viewMoreCalculations = true;
        while (pointer >= 0 && viewMoreCalculations) {
            MathematicalExpression expression = history[pointer];
                validateDataAndDisplayResult(expression);
            pointer--;
            viewMoreCalculations = IOStream.askForUsersPermission(" Would you like to view more" +
                    " from your previous calculations (Y - yes, N - No)");
            if (pointer < 0 && viewMoreCalculations) {
                System.out.println("\nThere is nothing more to show from the history!!\n");
                break;
            }
        }
    }

    public static boolean memoryIsFull() {
        return (arraySize == counter);
    }
}
