package calculator.intelligent.dsa;

public class Main {

    public static void main(String[] args) {
        boolean exit = false;
        do {
            IOStream.displayMenu();
            char choice = IOStream.getCharacter();
            if (Calculator.memoryIsFull() && Operations.fromChar(choice) == Operations.evaluateExpression) {
                continue;
            }
            try {
                exit = performTheChosenTask(Operations.fromChar(choice));
            } catch (InvalidOperatorException e) {
                System.out.println(e.getMessage());
            }

        } while (!exit);

    }

    private static boolean performTheChosenTask(Operations choice) throws InvalidOperatorException {
        boolean response = false;
        switch (choice) {
            case evaluateExpression -> evaluateExpression();
            case showHistory -> Calculator.showPreviousCalculations();
            default -> response = true;
        }
        return response;
    }

    private static void evaluateExpression() throws InvalidOperatorException {
        System.out.println("\n\tPlease Enter the values for evaluation\n");
        double firstValue = IOStream.getDouble("First");
        double secondValue = IOStream.getDouble("Second");
        if (Calculator.recentOperator != ' ') {
            boolean evaluateWithThePreviousOperator = IOStream.askForUsersPermission(getOperatorSuggestionMessage());
            if (evaluateWithThePreviousOperator) {
                Calculator.DisplayResultsUsingPreviousOperator(firstValue, secondValue);
                return;
            }
        }
        getOperatorAndEvaluateResults(firstValue, secondValue);
    }

    private static void getOperatorAndEvaluateResults(double firstValue, double secondValue) {
        while (true) {
            System.out.print("\n Please Choose an operator (eg. +, -, *, /): \t");
            char operator = IOStream.getCharacter();
            try {
                MathematicalExpression expression = new MathematicalExpression(firstValue, secondValue, operator);
                Calculator.validateDataAndDisplayResult(expression);
            } catch (InvalidOperatorException e) {
                System.out.println(e.getMessage());
                continue;
            }
            break;
        }
    }


    private static String getOperatorSuggestionMessage() {
        char operator = Calculator.recentOperator;
        String message = "Would you like to perform ";
        if (operator == '+') {
            message += "addition ";
        } else if (operator == '-') {
            message += "Subtraction ";
        } else if (operator == '*') {
            message += "Multiplication ";
        } else {
            message += "Division ";
        }
        message += "again?(y-yes, n-no)";
        return message;
    }
}
