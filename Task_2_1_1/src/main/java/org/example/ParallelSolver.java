package org.example;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.RejectedExecutionException;


/**
 * Класс параллельного решателя.
 */
public class ParallelSolver implements Solver {
    private int threadsNumber;
    private volatile boolean answer = false;

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
                if (isPrime(this.num)) {
                    return true;
                } else {
                    es.shutdownNow();
                    return false;
                }
            }
        }

        for (long num : arr) {
            tasks.add(new PrimeTask(num));
        }

        try {
            List<Future<Boolean>> listResult = new ArrayList<>();
            for (Callable<Boolean> task : tasks) {
                try {
                    listResult.add(es.submit(task));
                } catch (RejectedExecutionException e) {
                    return true;
                }
            }
            es.shutdown();
            for (Future<Boolean> elem : listResult) {
                if (!elem.get()) {
                    return true;
                }
            }
            return false;

        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }
}
