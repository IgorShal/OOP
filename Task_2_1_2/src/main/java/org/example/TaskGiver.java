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
    public boolean solve(long[] arr) {
        if (this.workersList.isEmpty()){
            throw new RuntimeException("No workers to solve");
        }
        this.tasks = splitTask(arr, this.workersList.size());
        while (!(tasks.stream().allMatch(Task::isDone) || tasks.stream().anyMatch(Task::getAnswer))) {
            for (Worker worker : workersList) {
                if (worker.getStatus() == Worker.WorkerStatus.DEAD) {
                    Task deadTask = getTaskByWorkerNumber(worker.getNumber());
                    if (!deadTask.isDone()){
                        deadTask.setWorkerNumber(-1);
                    }

                }
                else if (worker.getStatus() == Worker.WorkerStatus.READY){
                    try {
                        Task free = getFreeTask();
                        free.setWorkerNumber(worker.getWorkerNumber());
                        worker.setTaskNumber(free.getTaskNumber());
                        worker.solveTask(free);
                    }catch (RuntimeException ignored){
                    }
                }
            }

        }
        for (Worker worker : workersList) {
            if (worker.getStatus() != Worker.WorkerStatus.READY) {
                worker.interrupt();
            }
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

    private Worker getWorkerByTaskNumber(int number) {
        for (Worker worker : this.workersList) {
            if (worker.getTaskNumber() == number) {
                return worker;
            }
        }
        throw new RuntimeException("No worker with such task");
    }

    private Task getTaskByWorkerNumber(int number) {
        for (Task task : this.tasks) {
            if (task.getWorkerNumber() == number) {
                return task;
            }
        }
        throw new RuntimeException("No task with such worker number");
    }
}
