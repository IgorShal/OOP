package org.example;

import java.util.ArrayList;

/**
 * Класс деления.
 */
public class Div extends Function {
    /**
     * Конструктор деления.
     */
    Div() {
        super(2);
    }

    /**
     * Функция применения.
     */
    @Override
    public double apply(ArrayList<Double> vars) throws CalculatorException {
        if (super.countOfArgs != vars.size()) {
            throw new WrongCountOfArgumentsException("wrong count of args");
        }
        if (Math.abs(vars.get(1)) < 0.000000001) {
            throw new DivisionByZeroException("Division by zero");
        }
        return vars.get(0) / vars.get(1);
    }
}
