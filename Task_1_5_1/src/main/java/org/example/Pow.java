package org.example;

import java.util.ArrayList;

/**
 * Класс пова.
 */
public class Pow extends Function {
    /**
     * Конструктор пова.
     */
    Pow() {
        super(2);
    }

    /**
     * Функция применения.
     */
    @Override
    public double apply(ArrayList<Double> vars) throws Exception {
        if (super.countOfArgs != vars.size())
            throw new Exception("wrong count of args");
        return Math.pow(vars.get(0), vars.get(1));
    }
}
