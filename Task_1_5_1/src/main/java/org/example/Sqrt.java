package org.example;

import java.util.ArrayList;

/**
 * Класс корня.
 */
public class Sqrt extends Function {
    /**
     * Конструктор корня.
     */
    Sqrt() {
        super(1);
    }

    /**
     * Функция применения.
     */
    @Override
    public double apply(ArrayList<Double> vars) throws Exception {
        if (super.countOfArgs != vars.size())
            throw new Exception("wrong count of args");
        if (vars.get(0) < 0)
            throw new Exception("Sqrt from negative");
        return Math.sqrt(vars.get(0));
    }
}
