package org.example;

import java.util.ArrayList;


/**
 * Класс задачи.
 */
public class Task {
    private boolean answer;
    private boolean done;
    private int workerNumber;
    private int taskNumber;
    private ArrayList<Long> arr;

    /**
     * Конструктор.
     *
     * @param arr        Массив чисел.
     * @param taskNumber номер задачи.
     */
    public Task(ArrayList<Long> arr, int taskNumber) {
        this.done = false;
        this.taskNumber = taskNumber;
        this.workerNumber = -1;
        this.arr = arr;
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
     * Сеттер.
     */
    public void setAnswer(boolean answer) {
        this.done = true;
        this.answer = answer;
    }

    /**
     * Геттер.
     */
    public boolean getAnswer() {
        return answer;
    }

    /**
     * Геттер.
     */
    public ArrayList<Long> getArr() {
        return arr;
    }

    /**
     * Геттер.
     */
    public boolean isDone() {
        return done;
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
    public void setDone(boolean done) {
        this.done = done;

    }
}
