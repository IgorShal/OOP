package org.solver;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.UnknownHostException;
import java.nio.ByteBuffer;
import java.util.ArrayList;

/**
 * Класс для сериализация и десериализации.
 */
public class Serializer {
    /**
     * Конструктор.
     */
    private Serializer() {
    }

    /**
     * Метод для сериализация адреса.
     *
     * @param address адрес.
     */
    public static byte[] serializeAddress(String address) {
        return address.getBytes();
    }

    /**
     * Обрабатываем полученные данные из пакета броадкаста и получаем адрес сервера.
     */
    public static InetSocketAddress deserializeAddress(byte[] bytes) throws UnknownHostException {
        String msg = new String(bytes).split("\n")[0];
        String stringPort = msg.split(":")[1];
        String stringAdds = msg.split(":")[0].split("/")[1];

        int serverPort = Integer.parseInt(stringPort);
        System.out.println(stringAdds);
        InetAddress address = InetAddress.getByName(stringAdds);
        return new InetSocketAddress(address, serverPort);
    }

    /**
     * Метод сериализации задачи.
     *
     * @param task задача.
     * @return массив байтов.
     */
    public static byte[] serializeTask(Task task) throws IOException {
        ByteArrayOutputStream bytes =  new ByteArrayOutputStream();
        ByteBuffer sizeBuf = ByteBuffer.allocate(4);
        int size = task.getArr().size();
        sizeBuf.putInt(size);
        bytes.write(sizeBuf.array());
        for (Long num : task.getArr()){
            ByteBuffer buf = ByteBuffer.allocate(8);
            buf.position(0);
            buf.putLong(num);
            buf.position(0);
            bytes.write(buf.array());
        }
        return bytes.toByteArray();
    }
    /**
     * Десериализуем данные массив байтов в массив чисел.
     */
    public static ArrayList<Long> deserializeTaskIntoLongArr(byte[] bytes) {
        ArrayList<Long> task = new ArrayList<>();

        ByteBuffer buffer = ByteBuffer.wrap(bytes);
        buffer.position(0);

        int size = buffer.getInt();
        for (int i = 0; i < size; i++) {
            task.add(buffer.getLong());
        }
        System.out.println(task);
        assert task.size() == size;
        return task;
    }

    public static byte[] serializeAnswer(boolean answer) {
        ByteBuffer ansBuffer = ByteBuffer.allocate(4);
        if (answer) {
            ansBuffer.putInt(1);
        }
        ansBuffer.position(0);
        return ansBuffer.array();
    }

    public static boolean deserializeAnswer(byte[] bytes) {
        ByteBuffer buf = ByteBuffer.wrap(bytes);
        buf.position(0);
        int answer = buf.getInt();
        return answer > 0;
    }
}
