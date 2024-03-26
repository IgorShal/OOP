package org.solver;

/**
 * Класс рабочего.
 */
public class Worker {
    private WorkerStatus status;
    private int workerNumber;
    private int taskNumber;

    /**
     * Конструктор, номер рабочего и его задание ставим на -1.
     */
    public Worker() {
        this.workerNumber = -1;
        this.taskNumber = -1;
        this.status = WorkerStatus.READY;
    }

    /**
     * Метод решения задачи.
     *
     * @param task Задача.
     */
    public void solveTask(Task task) {
        return;
    }

    /**
     * Сеттер.
     */
    public void setNumber(int number) {
        this.workerNumber = number;
    }

    /**
     * Геттер.
     */
    public int getNumber() {
        return workerNumber;
    }

    /**
     * Метод для прерывания рабочего.
     */
    public void interrupt() {
        this.status = WorkerStatus.INTERRUPTED;
    }

    /**
     * Геттер.
     */
    public WorkerStatus getStatus() {
        return status;
    }

    /**
     * Сеттер.
     */
    public void setStatus(WorkerStatus status) {
        this.status = status;
    }

    /**
     * Сеттер.
     */
    public void setTaskNumber(int taskNumber) {
        this.taskNumber = taskNumber;
    }

    /**
     * Геттер.
     */
    public int getTaskNumber() {
        return taskNumber;
    }

    /**
     * Сеттер.
     */
    public void setWorkerNumber(int workerNumber) {
        this.workerNumber = workerNumber;
    }

    /**
     * Геттер.
     */
    public int getWorkerNumber() {
        return workerNumber;
    }

    /**
     * Возможные статусы рабочих.
     */
    public enum WorkerStatus {
        READY,
        DELETED,
        INTERRUPTED,
        WORKING,
        DEAD,


    }
}
