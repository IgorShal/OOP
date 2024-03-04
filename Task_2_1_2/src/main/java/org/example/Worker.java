package org.example;

public class Worker {
    private WorkerStatus status;
    private int workerNumber;
    private int taskNumber;
    public Worker(){
        this.workerNumber = -1;
        this.taskNumber = -1;
        this.status = WorkerStatus.READY;
    }
    public void solveTask(Task task){
        return;
    }

    public void setNumber(int number) {
        this.workerNumber = number;
    }

    public int getNumber() {
        return workerNumber;
    }
    public void interrupt(){
        this.status = WorkerStatus.INTERRUPTED;
    }
    public WorkerStatus getStatus() {
        return status;
    }

    public void setStatus(WorkerStatus status) {
        this.status = status;
    }

    public void setTaskNumber(int taskNumber) {
        this.taskNumber = taskNumber;
    }

    public int getTaskNumber() {
        return taskNumber;
    }

    public void setWorkerNumber(int workerNumber) {
        this.workerNumber = workerNumber;
    }

    public int getWorkerNumber() {
        return workerNumber;
    }

    public enum WorkerStatus{
        READY,
        DELETED,
        INTERRUPTED,
        WORKING,
        DEAD,


    }
}
