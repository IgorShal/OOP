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
    public double apply(ArrayList<Double> vars) throws CalculatorException {
        if (super.countOfArgs != vars.size()) {
            throw new WrongCountOfArgumentsException("wrong count of args");
        }
        if (vars.get(0) < 0) {
            throw new SqrtFromNegativeException("Sqrt from negative");
        }
        return Math.sqrt(vars.get(0));
    }
}
