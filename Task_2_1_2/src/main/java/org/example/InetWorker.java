package org.example;

/**
 * Класс интернет рабочего, по сути просто вспомогательный
 * класс для поддержки абстракции с воркерами и таскгивером.
 */
public class InetWorker extends Worker {
    private Server server;

    /**
     * Конструктор.
     */
    public InetWorker() {
        super();
        server = null;
    }

    /**
     * Метод решения задач, добавляет задачу в список задач сервера.
     *
     * @param task Задача.
     */
    @Override
    public void solveTask(Task task) {
        if (!this.server.tasks.contains(task)) {
            this.server.tasks.add(task);
        }

        if (this.server.tasks.size() == this.server.workers.size() &&
            !this.server.serverThread.isAlive()) {
            this.server.serverThread.start();
        }
    }

    /**
     * Сеттер.
     */
    public void setServer(Server server) {
        this.server = server;
    }

    /**
     * Геттер.
     */
    public Server getServer() {
        return server;
    }
}
