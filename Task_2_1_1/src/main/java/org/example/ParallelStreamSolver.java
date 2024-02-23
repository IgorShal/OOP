package org.example;

import java.util.Arrays;

/**
 * Класс параллельного через стримы решателя.
 */
public class ParallelStreamSolver implements Solver {
    /**
     * Метод проверки массива на наличие непростых.
     *
     * @param arr Массив.
     * @return Тру или фолз.
     */
    @Override
    public boolean hasNonPrimeNumber(long[] arr) {
        return Arrays.stream(arr).parallel().mapToObj(this::isPrime).anyMatch(x -> !x);
    }
}

