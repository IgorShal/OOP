package org.example;

import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        TaskGiver taskGiver = new TaskGiver();
        taskGiver.addWorker(new ThreadWorker());
        taskGiver.addWorker(new ThreadWorker());
        taskGiver.addWorker(new ThreadWorker());
        boolean answer = taskGiver.solve(new long[]{
            9223372036854775783L, 9223372036854775643L,9223372036854775643L,9223372036854775783L, 9223372036854775643L,9223372036854775643L});
        System.out.println(answer);

    }
}