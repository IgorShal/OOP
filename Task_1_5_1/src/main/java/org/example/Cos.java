package org.example;

import java.util.ArrayList;

/**
 * Класс косинуса.
 */
public class Cos extends Function {
    /**
     * Конструктор.
     */
    Cos() {
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
        return Math.cos(vars.get(0));
    }
}
