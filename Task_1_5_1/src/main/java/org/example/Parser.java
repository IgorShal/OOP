package org.example;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.EmptyStackException;
import java.util.List;
import java.util.Stack;

/**
 * Класс парсера - калькулятора.
 */
public class Parser {
    String lineToParse;
    FunctionFactory factory;

    /**
     * Конструктор.
     *
     * @param line Строка для обработки.
     */
    public Parser(String line) {
        this.lineToParse = line;
        factory = new FunctionFactory();
    }

    /**
     * Функция калькулирования.
     *
     * @return Дабл результат вычисления.
     * @throws Exception Исключения, если выражение задано неправильно.
     */
    public double calculate() throws Exception {
        double res = 0;
        List<String> array = Arrays.stream(this.lineToParse.split(" "))
                .filter(x -> !x.isEmpty()).toList();

        Stack<Double> stack = new Stack<>();
        Function cur = null;
        ArrayList<Double> vars = new ArrayList<>();
        for (int i = array.size() - 1; i >= 0; i--) {
            String elem = array.get(i);
            try {
                stack.add(Double.parseDouble(elem));

            } catch (NumberFormatException exception) {
                cur = this.factory.getFunction(elem);
                for (int j = 0; j < cur.countOfArgs; j++) {
                    try {
                        vars.add(stack.pop());
                    } catch (EmptyStackException e) {
                        throw new Exception("wrong expression");
                    }
                }
                stack.add(cur.apply(vars));
                vars.clear();
            }
        }
        res = stack.pop();
        if (!stack.isEmpty()) {
            throw new Exception("wrong expression");
        }

        return res;
    }

}
