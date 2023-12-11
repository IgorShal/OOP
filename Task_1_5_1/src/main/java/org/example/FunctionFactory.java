package org.example;

import java.util.HashMap;

/**
 * Фактори паттерн.
 */
public class FunctionFactory {
    HashMap<String, Function> functionMap;

    /**
     * Создаём хешмапу от стринга к функции.
     */
    public FunctionFactory() {
        this.functionMap = new HashMap<>();
        this.addBasicFunctions();
    }

    /**
     * Функция добавления функции.
     */
    public void addFunction(String name, Function function) {
        functionMap.put(name, function);
    }

    /**
     * Функция получения функции по имени.
     */
    public Function getFunction(String name) throws CalculatorException {
        if (functionMap.get(name) != null) {
            return functionMap.get(name);
        } else {
            throw new NoSuchFunctionException("No such function");
        }
    }

    /**
     * Добавляем базовые функции при создании.
     */
    private void addBasicFunctions() {
        this.addFunction("+", new Plus());
        this.addFunction("-", new Minus());
        this.addFunction("sqr", new Sqr());
        this.addFunction("sqrt", new Sqrt());
        this.addFunction("abs", new Abs());
        this.addFunction("*", new Mult());
        this.addFunction("/", new Div());
        this.addFunction("log", new Log());
        this.addFunction("pow", new Pow());
        this.addFunction("sin", new Sin());
        this.addFunction("cos", new Cos());
    }
}
