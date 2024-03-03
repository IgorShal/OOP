package org.example;

import java.io.IOException;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) throws IOException {
        TaskGiver taskGiver = new TaskGiver();
        //taskGiver.addWorker(new ThreadWorker());
        //taskGiver.addWorker(new ThreadWorker());
        //taskGiver.addWorker(new ThreadWorker());
        int port = 6000;
        Server server = new Server(port);

        Thread clientTh = new Thread(() -> {
            try {
                Client client = new Client(port);
                client.getTask(7000);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

        });
        Thread clientTh2 = new Thread(() -> {
            try {
                Client client = new Client(port);
                client.getTask(7000);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

        });
        Thread clientTh3 = new Thread(() -> {
            try {
                Client client = new Client(port);
                client.getTask(7000);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

        });

        clientTh2.start();
        clientTh3.start();
        clientTh.start();


        ArrayList<InetWorker> workers = server.findWorkers(5000);
        for (Worker worker:workers){
            taskGiver.addWorker(worker);
        }


        taskGiver.solve(new long[]{9223372036854775783L,9223372036854775783L, 9223372036854775643L,9223372036854775783L});
        System.out.println();
    }
}