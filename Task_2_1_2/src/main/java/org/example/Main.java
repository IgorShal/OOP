package org.example;

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
        //taskGiver.addWorker(new ThreadWorker());
        //taskGiver.addWorker(new ThreadWorker());
        //taskGiver.addWorker(new ThreadWorker());
        int port = 6000;
        Server server = new Server(port);

        ArrayList<Client> clients = new ArrayList<>();

        Thread clientTh2 = new Thread(() -> {
            try {
                Client client = new Client(port);
                clients.add(client);
                client.getTask(700000);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

        });

        Thread clientTh3 = new Thread(() -> {
            try {
                Client client = new Client(port);
                clients.add(client);
                client.getTask(700000);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

        });

        Thread clientTh1 = new Thread(() -> {
            try {
                Client client = new Client(port);
                clients.add(client);
                client.getTask(100000);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

        });
        clientTh3.start();
        clientTh2.start();
        clientTh1.start();


        ArrayList<InetWorker> workers = server.findWorkers(5000);
        for (Worker worker : workers) {
            taskGiver.addWorker(worker);
        }
        boolean answer;
        try {
            answer = taskGiver.solve(new long[]{1, 3, 5, 7, 11, 5, 17, 5});
        } catch (Exception e) {
            System.out.println("execption");
            throw e;
        }
        System.out.println(clientTh1.isAlive());
        System.out.println(clientTh2.isAlive());
        System.out.println(clientTh3.isAlive());
        System.out.println(server.serverThread.isAlive());

        System.out.println("Answer:" + answer);

    }
}