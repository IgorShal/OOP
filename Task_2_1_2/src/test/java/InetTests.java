import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Stream;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.solver.Client;
import org.solver.ClientMain;
import org.solver.InetWorker;
import org.solver.Serializer;
import org.solver.Server;
import org.solver.Task;
import org.solver.TaskGiver;
import org.solver.ThreadWorker;
import org.solver.Worker;



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
            //Arguments.of(new long[]{
            //    9223372036854775783L, 9223372036854775643L}, false),
            Arguments.of(new long[]{
                3, 4, 5, 6, 7, 8, 11}, true),
            Arguments.of(new long[]{
                2}, false),
            Arguments.of(new long[]{
                3}, false),
            Arguments.of(new long[]{
                4}, true)

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
                String[] args = new String[]{6000 + ""};
                ClientMain.main(args);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
        clientTh2.start();
        ArrayList<InetWorker> workers = this.server.findWorkers(500);
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
                String[] args = new String[]{6000 + ""};
                ClientMain.main(args);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
        Thread clientTh3 = new Thread(() -> {
            try {
                String[] args = new String[]{6000 + ""};
                ClientMain.main(args);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
        clientTh2.start();
        clientTh3.start();
        ArrayList<InetWorker> workers = server.findWorkers(500);
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
                String[] args = new String[]{6000 + ""};
                ClientMain.main(args);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
        Thread clientTh3 = new Thread(() -> {
            try {
                Client client = new Client(this.port);
                client.connect();
                client.clientChannel.close();
                client.getAndSolveTasks(10000);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
        clientTh2.start();
        clientTh3.start();
        ArrayList<InetWorker> workers = server.findWorkers(500);
        for (Worker worker : workers) {
            taskGiver.addWorker(worker);
        }
        Assertions.assertEquals(taskGiver.solve(arr), value);
        clientTh2.join();
        clientTh3.join();
    }

    /**
     * Тест проверки корректности isPrime и performTask.
     */
    @ParameterizedTest
    @MethodSource("provideArgues")
    void algorithmCorrectnessTest(long[] arr, boolean value) throws IOException {
        Client client = new Client(this.port);
        ArrayList<Long> arrayList = new ArrayList<>();
        Arrays.stream(arr).forEach(arrayList::add);
        Assertions.assertEquals(client.performTask(arrayList), value);
    }

    /**
     * Тест сериализации.
     */
    @ParameterizedTest
    @MethodSource("provideArgues")
    void serializeTest(long[] arr, boolean value) {
        ArrayList<Long> arrayList = new ArrayList<>();
        Arrays.stream(arr).forEach(arrayList::add);
        Task task1 = new Task(arrayList, 0);
        ArrayList<ByteBuffer> list = Serializer.serializeTask(task1);
        ArrayList<ByteBuffer> byteFlow = new ArrayList<>();
        for (ByteBuffer buf : list) {
            while (buf.position() != buf.limit()) {
                ByteBuffer newBuf = ByteBuffer.allocate(1);
                newBuf.put(buf.get());
                newBuf.position(0);
                byteFlow.add(newBuf);
            }
        }
        Assertions.assertEquals(
            arrayList,
            Serializer.deserializeTaskIntoLongArr(byteFlow)
        );
    }

}

