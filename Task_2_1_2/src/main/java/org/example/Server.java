package org.example;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.nio.LongBuffer;
import java.nio.channels.*;
import java.nio.charset.Charset;
import java.security.Key;

import java.util.ArrayList;
import java.util.Set;
import java.util.stream.Collectors;

import static java.lang.Thread.sleep;

public class Server {
    ServerSocketChannel serverChannel;
    Selector selector;
    Thread serverThread;
    ArrayList<Task> tasks;
    ArrayList<InetWorker> workers;
    SelectionKey serverKey;


    public Server(int port) throws IOException {
        this.serverChannel = ServerSocketChannel.open();
        this.serverChannel.configureBlocking(false);
        serverChannel.socket().bind(new InetSocketAddress("localhost", port));
        System.out.println("Start server on localhost with port " + port);
        this.selector = Selector.open();
        this.serverKey = serverChannel
            .register(this.selector, SelectionKey.OP_ACCEPT);
        this.serverKey.attach(serverChannel.socket());
        this.workers = new ArrayList<>();
        this.tasks = new ArrayList<>();
        this.serverThread = new Thread(() -> {
            try {
                checkChannels();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
    }

    public ArrayList<InetWorker> findWorkers(int time) throws IOException {
        long start = System.currentTimeMillis();
        while (System.currentTimeMillis() - start < time) {
            if (this.selector.select() == 0) {
                System.out.println("no Actions");
                continue;
            }
            Set<SelectionKey> selectedKeys = this.selector.selectedKeys();
            for (SelectionKey key : selectedKeys) {
                if (key.isAcceptable()) {
                    addConnection();
                }
            }
            selectedKeys.clear();
        }
        System.out.println("Found workers: " + this.workers.size());
        return this.workers;
    }

    public void checkChannels() throws IOException {
        System.out.println("Check channels started");
        while (!(tasks.stream().allMatch(Task::isDone) || tasks.stream().anyMatch(Task::getAnswer))) {
            if (this.selector.select() == 0) {
                System.out.println("no Actions");
                continue;
            }
            Set<SelectionKey> selectedKeys = this.selector.selectedKeys();
            for (SelectionKey key : selectedKeys) {
                Worker curr = (Worker) key.attachment();
                if (!key.isValid()) {
                    deleteWorker(curr);
                }
                if (key.isWritable()) {
                    if (curr.getStatus() == Worker.WorkerStatus.READY) {
                        try {
                            curr.setStatus(Worker.WorkerStatus.WORKING);
                            giveTask((SocketChannel) key.channel(), getTaskByWorkerNumber(curr.getNumber()));
                            key.interestOps(SelectionKey.OP_READ);
                        } catch (Exception ignored) {

                        }

                    }
                }
                if (key.isReadable()) {
                    getAnswer((SocketChannel) key.channel(), getTaskByWorkerNumber(curr.getNumber()));
                    curr.setStatus(Worker.WorkerStatus.READY);
                    key.interestOps(SelectionKey.OP_WRITE);
                }
            }
            selectedKeys.clear();
        }
        System.out.println("Check channels ended, tasks count = " + this.tasks.size());
        endConnection();
    }

    public void giveTask(SocketChannel channel, Task task) throws IOException {
        ByteBuffer send = ByteBuffer.allocate(task.getArr().size() * 8);
        send.position(0);
        for (Long num:task.getArr()){
            send.putLong(num);
        }
        send.position(0);


        int write = channel.write(send);
        System.out.println("Server wrote " + write);

    }

    public void getAnswer(SocketChannel channel, Task task) throws IOException {
        ByteBuffer message = ByteBuffer.allocate(4);
        channel.read(message);
        message.position(0);
        int answer = message.getInt();
        System.out.println("answer : " + answer);
        if (answer > 0) {
            task.setAnswer(true);
        } else {
            task.setAnswer(false);
        }
        task.setDone(true);
    }

    public void addConnection() throws IOException {
        SocketChannel clientChannel = this.serverChannel.accept();
        if (clientChannel.isConnectionPending()) {
            clientChannel.finishConnect();
        }
        InetWorker worker = new InetWorker();
        worker.setServer(this);
        this.workers.add(worker);
        System.out.println("Connection established");
        clientChannel.configureBlocking(false);
        SelectionKey current = clientChannel.register(this.selector, SelectionKey.OP_READ | SelectionKey.OP_WRITE);
        current.attach(worker);
    }

    public void endConnection() throws IOException {
        this.serverChannel.close();
        for (Worker worker:this.workers){
            deleteWorker(worker);
        }
    }
    public void deleteWorker(Worker worker) {
        worker.setStatus(Worker.WorkerStatus.DEAD);
    }

    private Worker getWorkerByTaskNumber(int number) {
        for (Worker worker : this.workers) {
            if (worker.getTaskNumber() == number) {
                return worker;
            }
        }
        throw new RuntimeException("No worker with such task");
    }

    private Task getTaskByWorkerNumber(int number) {
        for (Task task : this.tasks) {
            if (task.getWorkerNumber() == number) {
                return task;
            }
        }
        throw new RuntimeException("No task with such worker number");
    }

}
