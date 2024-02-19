package org.example;

import java.util.Arrays;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.RejectedExecutionException;
import java.util.stream.Collectors;
import java.util.stream.Stream;


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

        class PrimeTask implements Callable<Boolean> {
            final long num;

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
        Stream<Future<Boolean>> result;
        try {
            result = Arrays.stream(arr).mapToObj(PrimeTask::new).map(es::submit)
                .collect(Collectors.toList()).stream();
        } catch (RejectedExecutionException e) {
            return true;
        }
        es.shutdown();
        return result.anyMatch(elem -> {
            try {
                return !elem.get();
            } catch (InterruptedException | ExecutionException e) {
                throw new RuntimeException(e);
            }
        });

    }
}