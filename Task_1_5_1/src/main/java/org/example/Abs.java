package org.example;

import java.util.ArrayList;

/**
 * Класс модуля.
 */
public class Abs extends Function {
    /**
     * Конструктор модуля.
     */
    Abs() {
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
        return Math.abs(vars.get(0));
    }
}
