package org.example;

import java.util.ArrayList;

/**
 * Класс умножения.
 */
public class Mult extends Function {
    /**
     * Конструктор умножения.
     */
    Mult() {
        super(2);
    }

    /**
     * Функция применения.
     */
    @Override
    public double apply(ArrayList<Double> vars) throws Exception {
        if (super.countOfArgs != vars.size())
            throw new Exception("wrong count of args");
        return vars.get(0) * vars.get(1);
    }
}
