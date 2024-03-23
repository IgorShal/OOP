package org.solver;

import java.io.IOException;

public class ServerMain {
    public static void main(String[] args) throws IOException {
        if (args.length > 0){

        }else {
            throw new RuntimeException("No port specified");
        }
    }
}
