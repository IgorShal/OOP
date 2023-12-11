package org.example;

public class WrongExpressionException extends CalculatorException{
    public WrongExpressionException(String message) {
        super(message);
    }
}
