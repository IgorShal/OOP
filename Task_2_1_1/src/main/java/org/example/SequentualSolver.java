package org.example;

import java.util.Arrays;

/**
 * Класс последовательного решателя.
 */
public class SequentualSolver implements Solver {
    /**
     * Метод проверки массива на наличие непростых.
     *
     * @param arr Массив.
     * @return Тру или фолз.
     */
    @Override
    public boolean hasNonPrimeNumber(long[] arr) {
        return Arrays.stream(arr).anyMatch(x -> !isPrime(x));
    }

    /**
     * Метод проверки числа на простоту.
     *
     * @param num число.
     * @return тру если простое и фолз иначе.
     */
}
