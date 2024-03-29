package org.solver;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.net.SocketAddress;
import java.nio.ByteBuffer;
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
     * Метод для получения датаграммы из мультикаста.
     *
     * @return Датаграмма.
     */
    public DatagramPacket getMulticastDatagram() throws IOException {
        MulticastSocket socket = new MulticastSocket(this.port);
        InetAddress group = InetAddress.getByName("230.0.0.0");
        socket.joinGroup(group);
        byte[] buf = new byte[100];
        DatagramPacket packet = new DatagramPacket(buf, buf.length);
        socket.receive(packet);
        socket.close();
        return packet;
    }

    /**
     * Коннект к серверу.
     */
    public void connect() throws IOException {
        DatagramPacket packet = getMulticastDatagram();
        SocketAddress address = Serializer.deserializeAddress(packet.getData());

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
            byte[] info = getInfoFromServer(time);
            ArrayList<Long> task = Serializer.deserializeTaskIntoLongArr(info);
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
    public byte[] getInfoFromServer(long time) throws IOException {
        long start = System.currentTimeMillis();
        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        while (System.currentTimeMillis() - start < time) {
            ByteBuffer oneByte = ByteBuffer.allocate(1);
            oneByte.position(0);
            int res = this.clientChannel.read(oneByte);
            if (res > 0) {
                oneByte.position(0);
                byte cur = oneByte.get();
                bytes.write(cur);
            } else if (res == -1) {
                this.clientChannel.close();
                break;
            }
        }
        return bytes.toByteArray();
    }

    /**
     * Выполняем задание сервера.
     *
     * @param task Задание, здесь именно список лонгов,
     *             а не структура таск потому что сервер передаёт клиенту числа по одному.
     */
    public boolean performTask(ArrayList<Long> task) {
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
        byte[] ansBuffer = Serializer.serializeAnswer(answer);
        System.out.println("Client: i send to server"
            + this.clientChannel.write(ByteBuffer.wrap(ansBuffer)));
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
