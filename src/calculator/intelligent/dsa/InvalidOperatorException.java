package calculator.intelligent.dsa;

public class InvalidOperatorException extends Exception{
    private final String message;

    public InvalidOperatorException(String message) {
        this.message = message;
    }
    public String getMessage() {
        return message;
    }
}
