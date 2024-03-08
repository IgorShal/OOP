import org.example.Client;
import org.example.InetWorker;
import org.example.Server;
import org.example.TaskGiver;
import org.example.ThreadWorker;
import org.example.Worker;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.io.IOException;
import java.util.ArrayList;
import java.util.stream.Stream;

/**
 * Класс тестов.
 */
public class InetTests {
    int port;
    Server server;
    TaskGiver taskGiver;

    /**
     * Метод подготовки тестов.
     */
    @BeforeEach
    void prepareTaskGiver() {
        this.taskGiver = new TaskGiver();
        this.port = 6000;

    }

    /**
     * Метод отправки аргументов.
     *
     * @return поток аргументов.
     */
    private static Stream<Arguments> provideArgues() {
        return Stream.of(
            Arguments.of(new long[]{6, 8, 7, 13, 5, 9, 4}, true),
            Arguments.of(new long[]{
                20319251, 6997901, 6997927, 6997937, 17858849, 6997967,
                6998009, 6998029, 6998039, 20165149, 6998051, 6998053}, false),
            Arguments.of(new long[]{
                1, 2, 3, 5, 7, 11,
                13, 17, 19, 23, 29, 31}, false),
            Arguments.of(new long[]{
                4, 6, 8, 10, 12, 14, 16, 18, 20, 22, 30}, true),
            Arguments.of(new long[]{
                9223372036854775783L, 9223372036854775643L}, false)
        );
    }

    /**
     * Тест одного клиента.
     */
    @ParameterizedTest
    @MethodSource("provideArgues")
    void oneClientTest(long[] arr, boolean value) throws Exception {
        this.server = new Server(port);
        Thread clientTh2 = new Thread(() -> {
            try {
                Client client = new Client(this.port);
                client.getTask(700000);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
        clientTh2.start();
        ArrayList<InetWorker> workers = this.server.findWorkers(5000);
        for (Worker worker : workers) {
            taskGiver.addWorker(worker);
        }
        Assertions.assertEquals(taskGiver.solve(arr), value);
        clientTh2.join();

    }

    /**
     * Тест двух клиента.
     */
    @ParameterizedTest
    @MethodSource("provideArgues")
    void twoClientTest(long[] arr, boolean value) throws Exception {
        this.server = new Server(port);
        Thread clientTh2 = new Thread(() -> {
            try {
                Client client = new Client(this.port);
                client.getTask(700000);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
        Thread clientTh3 = new Thread(() -> {
            try {
                Client client = new Client(this.port);
                client.getTask(700000);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
        clientTh2.start();
        clientTh3.start();
        ArrayList<InetWorker> workers = server.findWorkers(5000);
        for (Worker worker : workers) {
            taskGiver.addWorker(worker);
        }
        Assertions.assertEquals(taskGiver.solve(arr), value);
        clientTh2.join();
        clientTh3.join();
    }

    /**
     * Тест одного потока.
     */
    @ParameterizedTest
    @MethodSource("provideArgues")
    void oneThreadTest(long[] arr, boolean value) throws Exception {
        taskGiver.addWorker(new ThreadWorker());
        Assertions.assertEquals(taskGiver.solve(arr), value);
    }

    /**
     * Тест двух потоков.
     */
    @ParameterizedTest
    @MethodSource("provideArgues")
    void twoThreadsTest(long[] arr, boolean value) throws Exception {
        taskGiver.addWorker(new ThreadWorker());
        taskGiver.addWorker(new ThreadWorker());
        Assertions.assertEquals(taskGiver.solve(arr), value);
    }

    /**
     * Два клиента один умер.
     */
    @ParameterizedTest
    @MethodSource("provideArgues")
    void twoClientTestOneDead(long[] arr, boolean value) throws Exception {
        this.server = new Server(port);
        Thread clientTh2 = new Thread(() -> {
            try {
                Client client = new Client(this.port);
                client.getTask(700000);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
        Thread clientTh3 = new Thread(() -> {
            try {
                Client client = new Client(this.port);
                client.clientChannel.close();
                client.getTask(700000);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
        clientTh2.start();
        clientTh3.start();
        ArrayList<InetWorker> workers = server.findWorkers(5000);
        for (Worker worker : workers) {
            taskGiver.addWorker(worker);
        }
        Assertions.assertEquals(taskGiver.solve(arr), value);
        clientTh2.join();
        clientTh3.join();
    }
}

