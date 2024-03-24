package org.solver;

import java.io.IOException;

public class ClientMain {
    public static void main(String[] args) throws IOException {
        if (args.length > 0) {
            int port = Integer.parseInt(args[0]);
            Client client = new Client(port);
            client.connect();
            int res = 0;
            while (res == 0) {
                res = client.getAndSolveTasks(10000);
            }

        } else {
            throw new RuntimeException("No port specified");
        }

    }
}
