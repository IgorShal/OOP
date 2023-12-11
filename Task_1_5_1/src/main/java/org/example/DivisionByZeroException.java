package org.example;

/**
 * Деление на 0.
 */
public class DivisionByZeroException extends CalculatorException {
    /**
     * Конструктор.
     *
     * @param message Сообщение.
     */
    public DivisionByZeroException(String message) {
        super(message);
    }
}
