package org.example;


import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;


/**
 * Класс параллельного решателя.
 */
public class ParallelSolver implements Solver {
    private int threadsNumber;
    private boolean answer = false;

    /**
     * Конструктор для задания количества потоков.
     *
     * @param count Количество потоков.
     */
    public ParallelSolver(int count) {
        threadsNumber = count;
    }

    /**
     * Метод проверки массива на наличие непростых.
     *
     * @param arr Массив.
     * @return Тру или фолз.
     */
    @Override
    public boolean hasNonPrimeNumber(long[] arr) {
        ExecutorService es = Executors.newFixedThreadPool(threadsNumber);
        List<Callable<Boolean>> tasks = new ArrayList<>();
        class PrimeTask implements Callable<Boolean> {
            long num;

            PrimeTask(long num) {
                this.num = num;
            }

            @Override
            public Boolean call() throws Exception {
                if (answer) {
                    return false;
                }
                if (isPrime(this.num)) {
                    return true;
                } else {
                    answer = true;
                    return false;
                }
            }
        }

        for (long num : arr) {
            tasks.add(new PrimeTask(num));
        }

        try {
            List<Future<Boolean>> listResult = es.invokeAll(tasks);
            es.shutdown();
            if (!es.awaitTermination(Long.MAX_VALUE, TimeUnit.SECONDS)) {
                es.shutdownNow();
            }
            for (Future<Boolean> elem : listResult) {
                if (!elem.get()) {
                    answer = false;
                    return true;
                }
            }
            return false;

        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    /**
     * Метод проверки числа на простоту.
     *
     * @param num число.
     * @return тру если простое и фолз иначе.
     */
    @Override
    public boolean isPrime(long num) {
        long sqrt = Math.round(Math.sqrt((double) num));
        if (num == 2) {
            return true;
        }
        for (long i = 2; i <= sqrt; i++) {
            if (num % i == 0) {
                return false;
            }
        }
        return true;
    }
}
