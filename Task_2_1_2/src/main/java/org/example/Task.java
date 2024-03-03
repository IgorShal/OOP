package org.example;

import java.util.ArrayList;

public class Task {
    private boolean answer;
    private boolean done;
    private int workerNumber;
    private int taskNumber;
    private ArrayList<Long> arr;
    public Task(ArrayList<Long> arr,int taskNumber){
        this.done = false;
        this.taskNumber = taskNumber;
        this.workerNumber = -1;
        this.arr = arr;
    }

    public void setWorkerNumber(int workerNumber) {
        this.workerNumber = workerNumber;
    }
    public int getWorkerNumber() {
        return workerNumber;
    }

    public void setAnswer(boolean answer) {
        this.done = true;
        this.answer = answer;
    }

    public boolean getAnswer() {
        return answer;
    }

    public ArrayList<Long> getArr() {
        return arr;
    }

    public boolean isDone() {
        return done;
    }
    public void setTaskNumber(int taskNumber) {
        this.taskNumber = taskNumber;
    }

    public int getTaskNumber() {
        return taskNumber;
    }

    public void setDone(boolean done) {
        this.done = done;
    }
}
