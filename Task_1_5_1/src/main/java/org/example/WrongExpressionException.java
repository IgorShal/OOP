package org.example;

/**
 * Класс исключений для неправильных выражений.
 */
public class WrongExpressionException extends CalculatorException {
    /**
     * Конструктор.
     *
     * @param message Сообщение.
     */
    public WrongExpressionException(String message) {
        super(message);
    }
}
