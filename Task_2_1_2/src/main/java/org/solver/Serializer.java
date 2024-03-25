package org.solver;

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
     * Метод сериализации задачи.
     *
     * @param task задача.
     * @return массив байтов.
     */
    public static ArrayList<ByteBuffer> serializeTask(Task task) {
        ByteBuffer size = ByteBuffer.allocate(4);
        size.position(0);
        size.putInt(task.getArr().size());
        size.position(0);
        ArrayList<ByteBuffer> res = new ArrayList<>();
        res.add(size);

        for (Long num : task.getArr()) {
            ByteBuffer send = ByteBuffer.allocate(8);
            send.position(0);
            send.putLong(num);
            send.position(0);
            res.add(send);
        }
        return res;
    }

    /**
     * Обрабатываем полученные данные из пакета броадкаста и получаем адрес сервера.
     */
    public static InetSocketAddress getAddressByBuffer(byte[] bytes) throws UnknownHostException {
        String msg = new String(bytes).split("\n")[0];
        String stringPort = msg.split(":")[1];
        String stringAdds = msg.split(":")[0].split("/")[1];

        int serverPort = Integer.parseInt(stringPort);
        System.out.println(stringAdds);
        InetAddress address = InetAddress.getByName(stringAdds);
        return new InetSocketAddress(address, serverPort);
    }

    /**
     * Десериализуем данные массив байтов в массив чисел.
     */
    public static ArrayList<Long> deserializeTaskIntoLongArr(ArrayList<ByteBuffer> bytes) {
        ArrayList<Long> task = new ArrayList<>();

        ByteBuffer sizeBuf = ByteBuffer.allocate(4);
        sizeBuf.position(0);

        for (int i = 0; i < 4; i++) {
            sizeBuf.put(bytes.get(i).get());
        }

        sizeBuf.position(0);
        int size = sizeBuf.getInt();

        assert size >= 0;

        for (int i = 0; i < size; i++) {
            ByteBuffer longBuf = ByteBuffer.allocate(8);
            longBuf.position(0);
            for (int j = 0; j < 8; j++) {
                longBuf.put(bytes.get(4 + i * 8 + j).get());
            }
            longBuf.position(0);
            task.add(longBuf.getLong());
        }
        System.out.println(task);
        assert task.size() == size;
        return task;
    }

}
