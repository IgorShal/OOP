package org.example;

public class InetWorker extends Worker {
    private Server server;

    public InetWorker() {
        super();
        server = null;
    }

    @Override
    public void solveTask(Task task) {
        if (!this.server.tasks.contains(task)){
            this.server.tasks.add(task);
        }

        if (this.server.tasks.size() == this.server.workers.size() && !this.server.serverThread.isAlive()){
            this.server.serverThread.start();
        }
    }

    public void setServer(Server server) {
        this.server = server;
    }


    public Server getServer() {
        return server;
    }
}
