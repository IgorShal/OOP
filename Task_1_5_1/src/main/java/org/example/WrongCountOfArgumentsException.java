package org.example;

/**
 * Неправильное количество аргументов у функции.
 */
public class WrongCountOfArgumentsException extends WrongExpressionException {
    /**
     * Конструктор.
     *
     * @param message Сообщение.
     */
    public WrongCountOfArgumentsException(String message) {
        super(message);
    }
}
