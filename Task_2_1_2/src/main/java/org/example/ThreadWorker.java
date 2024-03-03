package org.example;

import java.util.Arrays;



public class ThreadWorker extends Worker {

    public ThreadWorker() {
        super();
    }


    @Override
    public void solveTask(Task task) {
        Thread thread = new Thread(() -> this.solve(task));
        thread.start();

    }
    private void solve(Task task){
        this.setStatus(WorkerStatus.WORKING);
        task.setAnswer(task.getArr().stream().anyMatch(x -> !isPrime(x)));
        task.setDone(true);
        this.setStatus(WorkerStatus.READY);
        System.out.println("Worker ready:" + this.getWorkerNumber());
    }
    /**
     * Метод проверки числа на простоту.
     *
     * @param num число.
     * @return тру если простое и фолз иначе.
     */
    public boolean isPrime(long num) {
        long square = Math.round(Math.sqrt((double) num));
        for (long i = 2; i <= square; i++) {
            if (num % i == 0 || this.getStatus() == WorkerStatus.INTERRUPTED) {
                return false;
            }
        }
        return true;
    }
}
