package org.solver;

/**
 * Класс потока-рабочего.
 */
public class ThreadWorker extends Worker {
    /**
     * Конструктор.
     */
    public ThreadWorker() {
        super();
    }

    /**
     * Метод решения задачи.
     *
     * @param task Задача.
     */
    @Override
    public void solveTask(Task task) {
        Thread thread = new Thread(() -> this.solve(task));
        thread.start();

    }

    /**
     * Конкрутная реализация решения для потока-рабочего.
     *
     * @param task Задача.
     */
    private void solve(Task task) {
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
