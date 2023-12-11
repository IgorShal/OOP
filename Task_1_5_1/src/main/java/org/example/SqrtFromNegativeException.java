package org.example;

/**
 * Корень из отрицательного.
 */
public class SqrtFromNegativeException extends CalculatorException {
    /**
     * Конструктор.
     *
     * @param message Сообщение.
     */
    public SqrtFromNegativeException(String message) {
        super(message);
    }
}
