package org.example;

/**
 * Исклюение для несуществующей функции.
 */
public class NoSuchFunctionException extends WrongExpressionException {
    /**
     * Конструктор.
     *
     * @param message Сообщение.
     */
    public NoSuchFunctionException(String message) {
        super(message);
    }
}

