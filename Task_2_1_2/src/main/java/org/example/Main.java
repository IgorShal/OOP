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


        Thread clientTh2 = new Thread(() -> {
            try {
                Client client = new Client(port);
                client.clientChannel.close();
                client.getTask(7000);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

        });
        Thread clientTh3 = new Thread(() -> {
            try {
                Client client = new Client(port);
                client.clientChannel.close();
                client.getTask(7000);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

        });
        Thread clientTh1 = new Thread(() -> {
            try {
                Client client = new Client(port);
                client.clientChannel.close();
                client.getTask(100000);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

        });

        clientTh3.start();
        clientTh2.start();
        clientTh1.start();




        ArrayList<InetWorker> workers = server.findWorkers(5000);
        for (Worker worker:workers){
            taskGiver.addWorker(worker);
        }
        taskGiver.addWorker(new ThreadWorker());
        boolean answer;
        try{
            answer = taskGiver.solve(new long[]{1,3, 6,7,11,13,17,19});
        } catch (Exception e){
            return;
        }

        System.out.println(answer);
    }
}