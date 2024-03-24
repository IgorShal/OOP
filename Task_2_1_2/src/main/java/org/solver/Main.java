package org.solver;


import java.io.IOException;
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
        long[] arr = new long[]{
            1, 2, 3, 5, 7, 11,
            13, 17, 19,  6, 9223372036854775643L,6,9223372036854775643L,6,9223372036854775643L};
        int port = 6000;
        Server server = new Server(port);
        ArrayList<InetWorker> workers = server.findWorkers(10000);
        for (Worker worker : workers) {
            taskGiver.addWorker(worker);
        }
        System.out.println(taskGiver.solve(arr));

    }
}