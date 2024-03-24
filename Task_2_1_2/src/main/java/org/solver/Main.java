package org.solver;

import java.util.ArrayList;

/**
 * Мейн класс.
 */
public class Main {
    /**
     * Мейн метод.
     */
    public static void main(String[] args) throws Exception {
        TaskGiver taskGiver = new TaskGiver();
        ArrayList<Integer> ports = new ArrayList<>();
        long[] arr = new long[]{
            1, 2, 3, 4, 7, 6, 11};
        int port = 6000;
        Server server = new Server(port);
        ArrayList<InetWorker> workers = server.findWorkers(10000,ports);
        for (Worker worker : workers) {
            taskGiver.addWorker(worker);
        }
        System.out.println(taskGiver.solve(arr));
    }
}