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
            13, 17, 19, 24, 29, 31};
        int port = 6000;
        Server server = new Server(port);
        Thread clientTh2 = new Thread(() -> {
            try {
                Client client = new Client(port);
                client.connect();
                client.getTask(2000);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
        clientTh2.start();
        ArrayList<InetWorker> workers = server.findWorkers(1000);
        for (Worker worker : workers) {
            taskGiver.addWorker(worker);
        }
        System.out.println(taskGiver.solve(arr));
        clientTh2.join();
    }
}