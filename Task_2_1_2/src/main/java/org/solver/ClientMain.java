package org.solver;

import java.io.IOException;

public class ClientMain {
    public static void main(String[] args) throws IOException {
        if (args.length > 1){
            int port = Integer.parseInt(args[1]);
            Client client = new Client(port);
            client.connect();
            client.getTask(70000);

        }

    }
}
