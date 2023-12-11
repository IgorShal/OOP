package org.example;

/**
 * Логарифм от неположительного.
 */
public class LogNonPositiveException extends CalculatorException {
    /**
     * Конструктор.
     *
     * @param message Сообщение.
     */
    public LogNonPositiveException(String message) {
        super(message);
    }
}
