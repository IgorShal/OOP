package org.example;

/**
 * Класс решателя.
 */
public interface Solver {
    /**
     * Метод проверки массива на наличие непростых.
     *
     * @param arr Массив.
     * @return Тру или фолз.
     */
    boolean hasNonPrimeNumber(long[] arr);

    /**
     * Метод проверки числа на простоту.
     *
     * @param num число.
     * @return тру если простое и фолз иначе.
     */
    boolean isPrime(long num);
}
