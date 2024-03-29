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
    public default boolean isPrime(long num) {
        long square = Math.round(Math.sqrt((double) num));
        for (long i = 2; i <= square; i++) {
            if (num % i == 0) {
                return false;
            }
        }
        return true;
    }
}
