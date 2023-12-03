package org.example;

import java.util.ArrayList;

/**
 * Класс синуса.
 */
public class Sin extends Function {
    /**
     * Конструктор синуса.
     */
    Sin() {
        super(1);
    }

    /**
     * Функция применения.
     */
    @Override
    public double apply(ArrayList<Double> vars) throws Exception {
        if (super.countOfArgs != vars.size()) {
            throw new Exception("wrong count of args");
        }
        return Math.sin(vars.get(0));
    }
}
