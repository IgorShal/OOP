package org.example;

import java.util.ArrayList;

/**
 * Возведение в квадрат.
 */
public class Sqr extends Function {
    /**
     * Конструктор.
     */
    Sqr() {
        super(1);
    }

    /**
     * Функция применения.
     */
    @Override
    public double apply(ArrayList<Double> vars) throws CalculatorException {
        if (super.countOfArgs != vars.size()) {
            throw new WrongCountOfArgumentsException("wrong count of args");
        }
        return vars.get(0) * vars.get(0);
    }
}
