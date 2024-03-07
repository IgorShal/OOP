package org.example;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.util.ArrayList;

/**
 * Класс клиента.
 * Подключается к серверу, получает задачу и решает.
 */
public class Client {
    SocketChannel clientChannel;

    /**
     * Конструктор.
     *
     * @param port Порт сервера.
     */
    public Client(int port) throws IOException {
        this.clientChannel = SocketChannel.open();
        this.clientChannel.configureBlocking(false);
        this.clientChannel.connect(new InetSocketAddress("localhost", port));

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
    public void getTask(long time) throws IOException {

        long start = System.currentTimeMillis();
        ArrayList<Long> task = new ArrayList<>();
        int size = -1;
        while (System.currentTimeMillis() - start < time) {
            if (size == -1) {
                ByteBuffer sizeBuf = ByteBuffer.allocate(4);
                sizeBuf.position(0);
                int res = this.clientChannel.read(sizeBuf);

                if (res > 0) {
                    sizeBuf.position(0);
                    size = sizeBuf.getInt();
                } else if (res == -1) {
                    this.clientChannel.close();
                    return;
                }

            } else {
                while (task.size() < size) {
                    ByteBuffer longBuf = ByteBuffer.allocate(8);
                    longBuf.position(0);
                    while (this.clientChannel.read(longBuf) > 0) {
                        longBuf.position(0);
                        task.add(longBuf.getLong());
                    }
                }
                System.out.println("Client: I got task:" + task.toString());
                size = -1;
                this.performTask(task);
            }

        }


    }

    /**
     * Выполняем задание сервера.
     *
     * @param task Задание, здесь именно список лонгов, а не структура таск потому что сервер передаёт клиенту числа по одному.
     */
    public void performTask(ArrayList<Long> task) throws IOException {
        boolean answer = task.stream().anyMatch(x -> !isPrime(x));
        task.clear();
        sendAnswer(answer);
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
        //System.out.println("Client: i send to server" + this.clientChannel.write(ansBuffer));
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
