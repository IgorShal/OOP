package org.example;

import java.util.ArrayList;

/**
 * Класс распределителя задач.
 */
public class TaskGiver {
    private ArrayList<Worker> workersList;
    private ArrayList<Task> tasks;

    /**
     * Конструктор.
     */
    public TaskGiver() {
        this.workersList = new ArrayList<>();
        this.tasks = new ArrayList<>();
    }

    /**
     * Добавить рабочего в список рабочих.
     *
     * @param worker Рабочий.
     */
    public void addWorker(Worker worker) {
        worker.setNumber(workersList.size());
        workersList.add(worker);
    }

    /**
     * Метод для получения не назначенной задачи. (workerNumber == -1)
     *
     * @return Задача.
     */
    private Task getFreeTask() {
        for (Task task : tasks) {
            if (task.getWorkerNumber() == -1) {
                return task;
            }
        }
        throw new RuntimeException("No free task!");
    }

    /**
     * Основной метод решения.
     *
     * @param arr Получает массив лонгов.
     * @return Возвращает тру,если есть непростые в массиве и фолз иначе.
     * @throws Exception Если задачи так и не решены, когда все ворверы отключились.
     */
    public boolean solve(long[] arr) throws Exception {
        if (this.workersList.isEmpty()) {
            throw new RuntimeException("No workers to solve");
        }

        this.tasks = splitTask(arr, this.workersList.size());
        while (!(this.tasks.stream().allMatch(Task::isDone)
            || this.tasks.stream().anyMatch(Task::getAnswer)
            || this.workersList.stream().allMatch(
            x -> x.getStatus() == Worker.WorkerStatus.DELETED))) {
            for (Worker worker : workersList) {
                if (worker.getStatus() == Worker.WorkerStatus.DEAD) {
                    Task deadTask = getTaskByWorker(worker);
                    if (!deadTask.isDone()) {
                        deadTask.setWorkerNumber(-1);
                    }
                    worker.setStatus(Worker.WorkerStatus.DELETED);

                } else if (worker.getStatus() == Worker.WorkerStatus.READY) {
                    try {
                        if (getTaskByWorker(worker).isDone()) {
                            Task free = getFreeTask();
                            free.setWorkerNumber(worker.getWorkerNumber());
                            worker.setTaskNumber(free.getTaskNumber());
                            worker.solveTask(free);
                        }
                    } catch (RuntimeException e) {
                        try {
                            Task free = getFreeTask();
                            free.setWorkerNumber(worker.getWorkerNumber());
                            worker.setTaskNumber(free.getTaskNumber());
                            worker.solveTask(free);
                        } catch (Exception ignored) {
                            continue;
                        }

                    }
                }
            }


        }
        for (Worker worker : workersList) {
            if (worker.getStatus() != Worker.WorkerStatus.READY
                || worker.getStatus() != Worker.WorkerStatus.DELETED) {
                worker.interrupt();
            }
        }
        if (!(this.tasks.stream().allMatch(Task::isDone)
            || this.tasks.stream().anyMatch(Task::getAnswer))) {
            throw new Exception("No workers left, tasks weren't done");


        }
        this.workersList.clear();
        boolean answer = this.tasks.stream().anyMatch(Task::getAnswer);
        this.tasks.clear();
        return answer;
    }

    /**
     * Метод деления исходного массива на задачи для воркеров.
     *
     * @param arr          Массив.
     * @param workersCount Количество воркеров, если, например, в массиве 6 чисел и 3 воркера,
     *                     то получим 3 задания по 2 числу
     * @return Список задач.
     */
    private ArrayList<Task> splitTask(long[] arr, int workersCount) {
        ArrayList<Task> result = new ArrayList<>();
        for (int i = 0; i < workersCount; i++) {
            result.add(new Task(new ArrayList<>(), result.size()));

        }

        for (int i = 0; i < arr.length; i++) {
            result.get(i % workersCount).getArr().add(arr[i]);
        }
        return result;
    }

    /**
     * По рабочему получаем его задание.
     *
     * @param worker Рабочий.
     * @return Задание.
     */
    private Task getTaskByWorker(Worker worker) {
        for (Task task : this.tasks) {
            if (task.getWorkerNumber() == worker.getNumber()
                && task.getTaskNumber() == worker.getTaskNumber()) {
                return task;
            }
        }
        throw new RuntimeException("No task with such worker number");
    }
}
