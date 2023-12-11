package org.example;

/**
 * Неправильное количество аргументов у функции.
 */
public class WrongCountOFArgumentsException extends WrongExpressionException {
    /**
     * Конструктор.
     *
     * @param message Сообщение.
     */
    public WrongCountOFArgumentsException(String message) {
        super(message);
    }
}
