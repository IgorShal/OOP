package org.example;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.stream.Collectors;

public class TaskGiver {
    private ArrayList<Worker> workersList;
    private ArrayList<Task> tasks;

    public TaskGiver() {
        this.workersList = new ArrayList<>();
        this.tasks = new ArrayList<>();
    }

    public void addWorker(Worker worker) {
        worker.setNumber(workersList.size());
        workersList.add(worker);
    }
    private Task getFreeTask(){
        for (Task task:tasks){
            if (task.getWorkerNumber() == -1){
                return task;
            }
        }
        throw new RuntimeException("No free task!");
    }
    public boolean solve(long[] arr) throws Exception {
        if (this.workersList.isEmpty()){
            throw new RuntimeException("No workers to solve");
        }
        this.tasks = splitTask(arr, this.workersList.size());
        while (!(this.tasks.stream().allMatch(Task::isDone)
            || this.tasks.stream().anyMatch(Task::getAnswer)
            || this.workersList.stream().allMatch(x->x.getStatus() == Worker.WorkerStatus.DELETED)) ) {
            for (Worker worker : workersList) {
                if (worker.getStatus() == Worker.WorkerStatus.DEAD) {
                    Task deadTask = getTaskByWorker(worker);
                    if (!deadTask.isDone()){
                        deadTask.setWorkerNumber(-1);
                    }
                    worker.setStatus(Worker.WorkerStatus.DELETED);

                }

                else if (worker.getStatus() == Worker.WorkerStatus.READY){
                    try {
                        if (getTaskByWorker(worker).isDone()) {
                            Task free = getFreeTask();
                            free.setWorkerNumber(worker.getWorkerNumber());
                            worker.setTaskNumber(free.getTaskNumber());
                            worker.solveTask(free);
                        }
                    }catch (RuntimeException e){
                        try {
                            Task free = getFreeTask();
                            free.setWorkerNumber(worker.getWorkerNumber());
                            worker.setTaskNumber(free.getTaskNumber());
                            worker.solveTask(free);
                        } catch (Exception ignored){

                        }

                    }
                }
            }


        }
        for (Worker worker : workersList) {
            if (worker.getStatus() != Worker.WorkerStatus.READY) {
                worker.interrupt();
            }
        }
        if (!this.tasks.stream().allMatch(Task::isDone)){
            throw new Exception("No workers left, tasks weren't done");


        }
        return tasks.stream().anyMatch(Task::getAnswer);
    }

    private ArrayList<Task> splitTask(long[] arr, int workersCount) {
        ArrayList<Task> result = new ArrayList<>();
        for (int i = 0; i < workersCount; i++) {
            result.add(new Task(new ArrayList<>(),result.size()));

        }

        for (int i = 0; i < arr.length; i++) {
            result.get(i % workersCount).getArr().add(arr[i]);
        }
        return result;
    }


    private Task getTaskByWorker(Worker worker) {
        for (Task task : this.tasks) {
            if (task.getWorkerNumber() == worker.getNumber() && task.getTaskNumber() == worker.getTaskNumber()) {
                return task;
            }
        }
        throw new RuntimeException("No task with such worker number");
    }
}
