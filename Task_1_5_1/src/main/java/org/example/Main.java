package org.example;

/**
 * Мейн класс.
 */
public class Main {
    /**
     * Мейн функция.
     */
    public static void main(String[] args) throws CalculatorException {
        Parser parser = new Parser("+ + -5    1 + 3 2");

        System.out.println(parser.calculate());
    }
}