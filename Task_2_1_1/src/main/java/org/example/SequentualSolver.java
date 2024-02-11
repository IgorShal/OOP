package org.example;

/**
 * Класс последовательного решателя.
 */
public class SequentualSolver implements Solver {
    /**
     * Метод проверки массива на наличие непростых.
     * @param arr Массив.
     * @return Тру или фолз.
     */
    @Override
    public boolean hasNonPrimeNumber(long[] arr) {
        for (long num : arr) {
            if (!isPrime(num)) {
                return true;
            }

        }
        return false;
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
