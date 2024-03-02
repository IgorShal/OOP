package org.example;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

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
    public void getTask() throws IOException {
        while (true){
            ByteBuffer answer = ByteBuffer.allocate(4);
            answer.clear();
            while (this.clientChannel.read(answer) > 0) {
                // Flip the buffer to start reading
                System.out.println("Client get " + answer.toString());
            }
        }

    }
    public void performTask(byte[] task){
        System.out.println(task);
    }
}
