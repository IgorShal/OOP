package org.example;

import java.util.ArrayList;

/**
 * Класс логарифма.
 */
public class Log extends Function {
    /**
     * Конструктор логарифма.
     */
    Log() {
        super(1);
    }

    /**
     * Функция применения.
     */
    @Override
    public double apply(ArrayList<Double> vars) throws CalculatorException {
        if (super.countOfArgs != vars.size()) {
            throw new WrongCountOFArgumentsException("wrong count of args");
        }
        if (Math.abs(vars.get(0)) < 0.000000001 || vars.get(0) < 0) {
            throw new LogNonPositiveException("Log from zero or negative");
        }
        return Math.log(vars.get(0));
    }
}
