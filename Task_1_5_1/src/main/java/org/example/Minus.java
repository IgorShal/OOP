package org.example;

import java.util.ArrayList;

/**
 * Класс минуса.
 */
public class Minus extends Function {
    /**
     * Конструктор минуса.
     */
    Minus() {
        super(2);
    }

    /**
     * Функция применения.
     */
    @Override
    public double apply(ArrayList<Double> vars) throws Exception {
        if (super.countOfArgs != vars.size()) {
            throw new Exception("wrong count of args");
        }
        return vars.get(0) - vars.get(1);
    }
}