package org.example;

import java.util.ArrayList;

/**
 * Класс плюса.
 */
public class Plus extends Function {
    /**
     * Конструктор плюса.
     */
    Plus() {
        super(2);
    }

    /**
     * Функция применения.
     */
    @Override
    public double apply(ArrayList<Double> vars) throws CalculatorException {
        if (super.countOfArgs != vars.size()) {
            throw new WrongCountOFArgumentsException("wrong count of args");
        }
        return vars.get(0) + vars.get(1);
    }
}
