package org.solver;

import java.io.IOException;

/**
 * Класс входной для клиета.
 */
public class ClientMain {
    /**
     * Мейн метод, принимаем порт, конектимся и решаем задачу.
     */
    public static void main(String[] args) throws IOException {
        if (args.length > 0) {
            int port = Integer.parseInt(args[0]);
            Client client = new Client(port);
            client.connect(port);
            int res = 0;
            while (res == 0) {
                res = client.getAndSolveTasks(500);
            }
        } else {
            throw new RuntimeException("No port specified");
        }

    }
}