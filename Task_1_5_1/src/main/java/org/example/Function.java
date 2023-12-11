package org.example;

import java.util.ArrayList;

/**
 * Класс функции.
 */
public class Function {

    int countOfArgs;

    /**
     * Конструктор принимает количество аргументов функции.
     */
    Function(int countOfArgs) {
        this.countOfArgs = countOfArgs;
    }

    /**
     * Функция применения.
     */
    public double apply(ArrayList<Double> vars) throws CalculatorException {
        return 0;
    }

}
