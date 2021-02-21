package calculator.intelligent.dsa;

public enum Operations {
    evaluateExpression('1'),
    showHistory('2'),
    exit('3');

    public final char operation;

    Operations(char code) {
        this.operation = code;
    }

    public static Operations fromChar(char code) {
        return switch (code) {
            case '1' -> Operations.evaluateExpression;
            case '2' -> Operations.showHistory;
            default -> Operations.exit;
        };
    }
}
