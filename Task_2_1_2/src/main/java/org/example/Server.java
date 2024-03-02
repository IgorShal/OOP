package org.example;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.nio.charset.Charset;
import java.security.Key;

import java.util.ArrayList;
import java.util.Set;

import static java.lang.Thread.sleep;

public class Server {
    ServerSocketChannel serverChannel;
    Selector selector;
    SelectionKey serverKey;
    ArrayList<SocketChannel> sockets;


    public Server(int port) throws IOException {

        this.serverChannel = ServerSocketChannel.open();
        this.serverChannel.configureBlocking(false);
        serverChannel.socket().bind(new InetSocketAddress("localhost", port));
        System.out.println("Start server on localhost with port " + port);
        this.selector = Selector.open();
        this.sockets = new ArrayList<>();

        this.serverKey = serverChannel
            .register(this.selector, SelectionKey.OP_ACCEPT);
        this.serverKey.attach(serverChannel.socket());

    }

    public void checkChannels() throws IOException {
        while (true) {
            if (this.selector.select() == 0) {
                System.out.println("no Actions");
                continue;
            }
            Set<SelectionKey> selectedKeys = this.selector.selectedKeys();
            for (SelectionKey key : selectedKeys) {
                if (key.isAcceptable()) {

                    SocketChannel clientChannel = this.serverChannel.accept();
                    if (clientChannel.isConnectionPending()) {
                        clientChannel.finishConnect();
                    }
                    System.out.println("accepted");
                    clientChannel.configureBlocking(false);
                    clientChannel.register(this.selector, SelectionKey.OP_READ | SelectionKey.OP_WRITE);


                }
                if (key.isWritable()) {
                    ByteBuffer send = ByteBuffer.allocate(4);

                    send.putInt(40000000);
                    send.position(0);
                    SocketChannel clientChannel = (SocketChannel)key.channel();
                    int write = clientChannel.write(send);
                    System.out.println(write);
                    key.interestOps(SelectionKey.OP_READ);
                }
                if (key.isReadable()) {
                    ByteBuffer send = ByteBuffer.allocate(4);
                    SocketChannel clientChannel = (SocketChannel)key.channel();
                    clientChannel.read(send);
                    System.out.println("SERVER:" + send);
                }
            }
            selectedKeys.clear();
        }
    }

    public void breakConnection(SelectionKey key) throws IOException {
        System.out.println("Connection broke");
        Socket connection = (Socket) key.attachment();
        connection.close();

    }

    public void addConnection() throws IOException {
        System.out.println("Connection established");
        Socket newConnection = this.serverChannel.socket().accept();
        newConnection.getChannel().configureBlocking(false);
        SelectionKey currentKey = newConnection.getChannel().
            register(selector, SelectionKey.OP_READ | SelectionKey.OP_WRITE);
        currentKey.attach(newConnection);
    }

    public void giveTask(SelectionKey key) throws IOException {
        Socket connection = (Socket) key.attachment();
        connection.getOutputStream().write(40404);
    }

    public void getAnswer(SelectionKey key) throws IOException {
        Socket connection = (Socket) key.attachment();
        int count = connection.getInputStream().read();
        System.out.println(count);
    }

}
