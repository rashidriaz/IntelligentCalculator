package calculator.intelligent.dsa;

public class MathematicalExpression {
    public final double firstNumber;
    public final double secondNumber;
    public final char operator;

    public MathematicalExpression(double firstValue, double secondValue, char operator) {
        this.firstNumber = firstValue;
        this.secondNumber = secondValue;
        this.operator = operator;
    }
}
