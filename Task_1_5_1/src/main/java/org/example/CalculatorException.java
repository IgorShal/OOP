package org.example;

/**
 * Общий класс эксепшенов калькулятора.
 */
public class CalculatorException extends Exception {
    /**
     * Конструктор.
     *
     * @param message Сообщение.
     */
    public CalculatorException(String message) {
        super(message);
    }
}
