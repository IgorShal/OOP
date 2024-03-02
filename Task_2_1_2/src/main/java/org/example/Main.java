package org.example;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException, InterruptedException {

        Thread serverThread = new Thread(() -> {
            try {
                Server server = new Server(9060);
                server.checkChannels();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
        serverThread.start();
        Client client = new Client(9060);
        client.getTask();
    }
}