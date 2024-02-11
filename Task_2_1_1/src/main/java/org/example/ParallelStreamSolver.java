package org.example;

import java.util.Arrays;

/**
 * Класс параллельного через стримы решателя.
 */
public class ParallelStreamSolver implements Solver {
    /**
     * Метод проверки массива на наличие непростых.
     * @param arr Массив.
     * @return Тру или фолз.
     */
    @Override
    public boolean hasNonPrimeNumber(long[] arr) {
        return Arrays.stream(arr).parallel().mapToObj(this::isPrime).anyMatch(x -> !x);
    }

    /**
     * Метод проверки числа на простоту.
     * @param num число.
     * @return тру если простое и фолз иначе.
     */
    @Override
    public boolean isPrime(long num) {
        if (num == 2) {
            return true;
        }
        long square = Math.round(Math.sqrt((double) num));
        for (long i = 2; i <= square; i++) {
            if (num % i == 0) {
                return false;
            }
        }
        return true;
    }
}

