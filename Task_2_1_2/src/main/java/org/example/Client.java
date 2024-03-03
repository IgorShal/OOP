package org.example;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.ByteBuffer;
import java.nio.IntBuffer;
import java.nio.channels.SocketChannel;
import java.util.ArrayList;

public class Client {
    SocketChannel clientChannel;

    public Client(int port) throws IOException {
        this.clientChannel = SocketChannel.open();
        this.clientChannel.configureBlocking(false);
        this.clientChannel.connect(new InetSocketAddress("localhost", port));

        while (!this.clientChannel.finishConnect()) {
            System.out.println("still connecting");
        }
        System.out.println("client connected");
    }
    public void getTask(long time) throws IOException {
        long start = System.currentTimeMillis();
        ArrayList<Long> task = new ArrayList<>();
        while (System.currentTimeMillis() - start < time){
            ByteBuffer longBuf = ByteBuffer.allocate(8);
            longBuf.position(0);
            while (this.clientChannel.read(longBuf) > 0){
                longBuf.position(0);
                task.add(longBuf.getLong());
            }

        }
        System.out.println("Client got task:" + task.toString());
        this.performTask(task);

    }
    public void performTask(ArrayList<Long> task) throws IOException {
        boolean answer = task.stream().anyMatch(x -> !isPrime(x));
        sendAnswer(answer);
    }
    public void sendAnswer(boolean answer) throws IOException {
        ByteBuffer ansBuffer = ByteBuffer.allocate(4);

        if (answer){
            ansBuffer.putInt(1);
        }
        ansBuffer.position(0);
        System.out.println("send server :" + this.clientChannel.write(ansBuffer));
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
