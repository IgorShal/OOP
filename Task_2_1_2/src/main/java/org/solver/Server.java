package org.solver;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.ArrayList;
import java.util.Set;

/**
 * Класс сервера, отвечает за общение с клиентами - рабочими.
 * Получает набор заданий от ТаскГивера и распределяет между клиентами.
 */
public class Server {
    ServerSocketChannel serverChannel;
    Selector selector;
    public Thread serverThread;
    ArrayList<Task> tasks;
    int port;
    ArrayList<InetWorker> workers;
    SelectionKey serverKey;

    /**
     * Конструктор сервера, сервер создаётся на локалхосте.
     *
     * @param port порт.
     */
    public Server(int port) throws IOException {
        this.serverChannel = ServerSocketChannel.open();
        this.port = port;
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

    /**
     * В течение определённого времени ждём клиентов для работы.
     *
     * @param time Время.
     * @return Список готовых рабочих.
     */
    public ArrayList<InetWorker> findWorkers(int time) throws IOException {
        long start = System.currentTimeMillis();

        DatagramSocket socket = new DatagramSocket();
        String address = this.serverChannel.getLocalAddress().toString() + "\n";


        while (System.currentTimeMillis() - start < time) {
            if (System.currentTimeMillis() % 100 == 0) {
                InetAddress group = InetAddress.getByName("230.0.0.0");

                byte[] message = Serializer.serializeAddress(address);
                DatagramPacket packet
                    = new DatagramPacket(message, message.length, group, port);
                socket.send(packet);


                this.selector.wakeup();
                if (this.selector.select() == 0) {
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
        }
        socket.close();
        this.serverChannel.close();
        System.out.println("Found workers: " + this.workers.size());
        return this.workers;
    }

    /**
     * Метод для проверки каналов на события.
     */
    public void checkChannels() throws IOException {
        System.out.println("Check channels started");
        while (!(tasks.stream().allMatch(Task::isDone)
            || tasks.stream().anyMatch(Task::getAnswer))) {
            this.selector.wakeup();
            Set<SelectionKey> keys = this.selector.keys();
            if (keys.stream().noneMatch(SelectionKey::isValid)) {
                break;
            }
            if (this.selector.select() == 0) {
                continue;
            }

            Set<SelectionKey> selectedKeys = this.selector.selectedKeys();
            for (SelectionKey key : selectedKeys) {
                Worker currWorker = (Worker) key.attachment();
                try {
                    if (!key.isValid()) {
                        deleteWorker(currWorker);
                        System.out.println("Worker dead:" + currWorker.getWorkerNumber());
                        key.channel().close();
                        continue;
                    }
                    if (key.isWritable()) {
                        if (currWorker.getStatus() == Worker.WorkerStatus.READY) {
                            Task task = getTaskByWorker(currWorker);
                            if (!task.isDone()) {
                                currWorker.setStatus(Worker.WorkerStatus.WORKING);
                                giveTask((SocketChannel) key.channel(), task);
                                key.interestOps(SelectionKey.OP_READ);
                            }
                        }
                    } else if (key.isReadable()) {
                        getAnswer((SocketChannel) key.channel(), getTaskByWorker(currWorker));
                        currWorker.setStatus(Worker.WorkerStatus.READY);
                        key.interestOps(SelectionKey.OP_WRITE);
                    }
                } catch (IOException e) {
                    System.out.println("Worker dead:" + currWorker.getWorkerNumber());
                    deleteWorker(currWorker);
                    key.channel().close();
                }
            }
            selectedKeys.clear();
        }
        System.out.println("Check channels ended, tasks count = " + this.tasks.size());
        endConnection();
    }

    /**
     * Даём задания клиенту.
     */
    public void giveTask(SocketChannel channel, Task task) throws IOException {
        byte[] bytes = Serializer.serializeTask(task);
        channel.write(ByteBuffer.wrap(bytes));
        System.out.println("Server: I send to worker " + task.getWorkerNumber());
    }


    /**
     * Получаем ответ от клиента.
     */
    public void getAnswer(SocketChannel channel, Task task) throws IOException {
        ByteBuffer message = ByteBuffer.allocate(4);
        if (channel.read(message) == -1) {
            throw new IOException("Worker dead");
        }
        boolean answer = Serializer.deserializeAnswer(message.array());
        System.out.println("Server: I get " + answer + " from " + task.getWorkerNumber());
        task.setAnswer(answer);
        task.setDone(true);

    }

    /**
     * Добавляем новое соединение.
     */
    public void addConnection() {
        try {
            SocketChannel clientChannel = this.serverChannel.accept();
            if (clientChannel.isConnectionPending()) {
                clientChannel.finishConnect();
            }
            InetWorker worker = new InetWorker();
            worker.setServer(this);
            this.workers.add(worker);
            System.out.println("Connection established");
            clientChannel.configureBlocking(false);
            SelectionKey current = clientChannel.register(this.selector,
                SelectionKey.OP_READ | SelectionKey.OP_WRITE);
            current.attach(worker);
        } catch (IOException ignored) {
            return;
        }

    }

    /**
     * Закрываем все соединения(каналы) после выполнения задания.
     */
    public void endConnection() throws IOException {
        Set<SelectionKey> set = this.selector.keys();
        this.serverChannel.close();
        for (SelectionKey key : set) {
            key.channel().close();
        }

        this.selector.close();
        this.serverChannel.socket().close();
        for (Worker worker : this.workers) {
            worker.setStatus(Worker.WorkerStatus.DELETED);
        }
    }

    /**
     * Меняем статус на мертвого для отключённых рабочих.
     *
     * @param worker Рабочий.
     */
    public void deleteWorker(Worker worker) {
        worker.setStatus(Worker.WorkerStatus.DEAD);
    }

    /**
     * По рабочему получаем его задание.
     *
     * @param worker Рабочий.
     * @return Задание.
     */
    private Task getTaskByWorker(Worker worker) {
        for (Task task : this.tasks) {
            if (task.getWorkerNumber() == worker.getNumber()
                && task.getTaskNumber() == worker.getTaskNumber()) {
                return task;
            }
        }
        throw new RuntimeException("No task with such worker number");
    }

}
