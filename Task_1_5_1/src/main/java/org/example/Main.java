package org.example;

import java.util.ArrayList;

/**
 * Мейн класс.
 */
public class Main {
    /**
     * Мейн функция.
     */
    public static void main(String[] args) throws CalculatorException {
        ArrayList<Integer> arr = new ArrayList<>();
        arr.add(1);
        arr.add(2);
        arr.add(3);
        var arr1 = arr.clone();

        arr1.remove(1);
        System.out.println(arr1);

    }
}