package org.solver;

import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.net.StandardSocketOptions;
import java.net.UnknownHostException;
import java.nio.ByteBuffer;
import java.nio.channels.DatagramChannel;
import java.nio.channels.SocketChannel;
import java.util.ArrayList;

/**
 * Класс клиента.
 * Подключается к серверу, получает задачу и решает.
 */
public class Client {
    public SocketChannel clientChannel;
    public int port;

    /**
     * Конструктор.
     *
     * @param port Порт сервера.
     */
    public Client(int port) throws IOException {
        this.clientChannel = SocketChannel.open();
        this.clientChannel.configureBlocking(false);
        this.port = port;
    }

    /**
     * Обрабатываем полученные данные из пакета броадкаста и получаем адрес сервера.
     */
    public InetSocketAddress getAddressByBuffer(ByteBuffer buffer) throws UnknownHostException {
        buffer.flip();
        byte[] bytes = new byte[buffer.remaining()];
        buffer.get(bytes);

        String msg = new String(bytes);
        String stringPort = msg.split(":")[1];
        String stringAdds = msg.split(":")[0].split("/")[1];

        int serverPort = Integer.parseInt(stringPort);
        System.out.println(stringAdds);
        InetAddress address = InetAddress.getByName(stringAdds);
        return new InetSocketAddress(address, serverPort);
    }

    /**
     * Коннект к серверу.
     */
    public void connect(int port) throws IOException {
        DatagramChannel datagramChannel = DatagramChannel.open();

        datagramChannel.bind(new InetSocketAddress("localhost", port));

        datagramChannel.setOption(StandardSocketOptions.SO_BROADCAST, true);
        ByteBuffer buffer = ByteBuffer.allocate(100);
        buffer.position(0);
        datagramChannel.receive(buffer);
        SocketAddress address = getAddressByBuffer(buffer);
        datagramChannel.close();
        System.out.println(address);
        this.clientChannel.connect(address);

        while (!this.clientChannel.finishConnect()) {
            System.out.println("still connecting");
        }
        System.out.println("client connected");

    }

    /**
     * Получаем задание от сервера в течение времени.
     * Сначала сервер отправляет количество чисел, потом клиент ждёт, пока не придут все числа.
     *
     * @param time Время.
     */
    public int getAndSolveTasks(long time) throws IOException {
        try {
            ArrayList<Long> task = parseBytes(getInfoFromServer(time));
            boolean answer = this.performTask(task);
            this.sendAnswer(answer);
            return 0;
        } catch (Exception e) {
            return -1;
        }


    }

    /**
     * Получаем данные от сервера.
     */
    public ArrayList<ByteBuffer> getInfoFromServer(long time) throws IOException {
        long start = System.currentTimeMillis();
        ArrayList<ByteBuffer> bytes = new ArrayList<>();
        while (System.currentTimeMillis() - start < time) {
            ByteBuffer sizeBuf = ByteBuffer.allocate(1);
            sizeBuf.position(0);
            int res = this.clientChannel.read(sizeBuf);
            if (res > 0) {
                sizeBuf.position(0);
                bytes.add(sizeBuf);
            } else if (res == -1) {
                this.clientChannel.close();
                break;
            }
        }
        return bytes;
    }

    /**
     * Десериализуем данные массив байтов в массив чисел.
     */
    public ArrayList<Long> parseBytes(ArrayList<ByteBuffer> bytes) {
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


    /**
     * Выполняем задание сервера.
     *
     * @param task Задание, здесь именно список лонгов,
     *             а не структура таск потому что сервер передаёт клиенту числа по одному.
     */
    public boolean performTask(ArrayList<Long> task) throws IOException {
        boolean answer = task.stream().anyMatch(x -> !isPrime(x));
        task.clear();
        return answer;
    }

    /**
     * Отправляем ответ серверу.
     *
     * @param answer Ответ.
     */
    public void sendAnswer(boolean answer) throws IOException {
        ByteBuffer ansBuffer = ByteBuffer.allocate(4);
        if (answer) {
            ansBuffer.putInt(1);
        }
        ansBuffer.position(0);
        System.out.println("Client: i send to server"
            + this.clientChannel.write(ansBuffer));
    }

    /**
     * Метод проверки числа на простоту.
     *
     * @param num число.
     * @return тру если простое и фолз иначе.
     */
    public boolean isPrime(long num) {
        long square = Math.round(Math.sqrt((double) num));
        for (long i = 2; i <= square; i++) {
            if (num % i == 0 || !this.clientChannel.isOpen()) {
                return false;
            }
        }
        return true;
    }

}
