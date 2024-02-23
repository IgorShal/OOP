package org.example;

/**
 * Мейн класс.
 */
public class Main {
    /**
     * Мейн метод.
     */
    public static void main(String[] args) {
        Solver solver = new ParallelStreamSolver();
        long start = System.currentTimeMillis();
        boolean res = solver.hasNonPrimeNumber(new long[]{1, 3, 3});
        System.out.println(res);
        long end = System.currentTimeMillis();
        System.out.println(end - start);
    }
}