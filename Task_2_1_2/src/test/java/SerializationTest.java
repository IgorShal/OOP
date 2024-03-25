import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.solver.Serializer;
import org.solver.Task;
import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Stream;

public class SerializationTest {
    /**
     * Метод отправки аргументов.
     *
     * @return поток аргументов.
     */
    private static Stream<Arguments> provideTaskArgues() {
        return Stream.of(
            Arguments.of(new Task(arrayToArraylist(new long[]{6, 8, 7, 13, 5, 9, 4}), 0)),
            Arguments.of(new Task(arrayToArraylist(new long[]{
                20319251, 6997901, 6997927, 6997937, 17858849, 6997967,
                6998009, 6998029, 6998039, 20165149, 6998051, 6998053}), 0)),
            Arguments.of(new Task(arrayToArraylist(new long[]{
                1, 2, 3, 5, 7, 11,
                13, 17, 19, 23, 29, 31}), 0)),
            Arguments.of(new Task(arrayToArraylist(new long[]{
                4, 6, 8, 10, 12, 14, 16, 18, 20, 22, 30}), 0)),
            Arguments.of(new Task(arrayToArraylist(new long[]{
                9223372036854775783L, 9223372036854775643L}), 0)),
            Arguments.of(new Task(arrayToArraylist(new long[]{
                3, 4, 5, 6, 7, 8, 11}), 0)),
            Arguments.of(new Task(arrayToArraylist(new long[]{
                2}), 0)),
            Arguments.of(new Task(arrayToArraylist(new long[]{
                3}), 0)),
            Arguments.of(new Task(arrayToArraylist(new long[]{
                4}), 0))

        );
    }

    private static ArrayList<Long> arrayToArraylist(long[] arr) {
        ArrayList<Long> arrayList = new ArrayList<>();
        Arrays.stream(arr).forEach(arrayList::add);
        return arrayList;
    }

    /**
     * Тест сериализации задачи.
     */
    @ParameterizedTest
    @MethodSource("provideTaskArgues")
    void serializeTaskTest(Task task) throws IOException {
        byte[] bytes = Serializer.serializeTask(task);
        Assertions.assertEquals(
            task.getArr(), Serializer.deserializeTaskIntoLongArr(bytes));
    }

    /**
     * Тест сериализации адреса.
     * Тут заметен костыль из кода, что я подаю \n в конце адреса при отправке,
     * Это нужно, чтобы клиент понял, когда адрес кончается.
     * Наверняка, можно сделать умнее, но я пока ничего лучше не придумал:P.
     *
     * @throws UnknownHostException Для неправильно введённого ip.
     */
    @Test
    void serializeAddressTest() throws UnknownHostException {
        InetAddress address = InetAddress.getByName("127.0.0.1");
        int port = 2000;
        Assertions.assertEquals(
            new InetSocketAddress(address, port),
            Serializer.deserializeAddress(Serializer.serializeAddress(address + ":" + port + "\n"))
        );
        address = InetAddress.getByName("0.0.0.0");
        port = 5;
        Assertions.assertEquals(
            new InetSocketAddress(address, port),
            Serializer.deserializeAddress(Serializer.serializeAddress(address + ":" + port + "\n"))
        );

        address = InetAddress.getByName("255.255.255.255");
        port = 0;
        Assertions.assertEquals(
            new InetSocketAddress(address, port),
            Serializer.deserializeAddress(Serializer.serializeAddress(address + ":" + port + "\n"))
        );

    }

    /**
     * Тест для проверки работы сериализации ответа.
     */
    @Test
    void serializeAnswerTest(){
        boolean answer = true;
        Assertions.assertEquals(
            answer,
            Serializer.deserializeAnswer(
                Serializer.serializeAnswer(answer)
            )
        );
        answer = false;
        Assertions.assertEquals(
            answer,
            Serializer.deserializeAnswer(
                Serializer.serializeAnswer(answer)
            )
        );
    }
}
