package onimko;

import java.util.LinkedList;
import java.util.Queue;

public class BlockedRunnableQueue {

    private final Queue<Runnable> tasks = new LinkedList<>();

    public BlockedRunnableQueue() {
        new Thread(() -> {
            while (true) getTask();
        }).start();
    }

    private synchronized Runnable getTask() {
        while (tasks.isEmpty()) {
            try {
                wait();
            } catch (InterruptedException e) {
                System.out.println(e.getMessage());
            }
        }
        return tasks.poll();
    }

    public synchronized void putTask(Runnable task) {
        tasks.add(task);
        notifyAll();
    }
}