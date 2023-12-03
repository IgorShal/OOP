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
    public double apply(ArrayList<Double> vars) throws Exception {
        if (super.countOfArgs != vars.size()) {
            throw new Exception("wrong count of args");
        }
        if (Math.abs(vars.get(1)) < 0.000000001){
            throw new Exception("Division by zero");
        }
        return vars.get(0) / vars.get(1);
    }
}
