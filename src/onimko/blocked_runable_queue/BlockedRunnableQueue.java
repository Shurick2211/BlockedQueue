package onimko.blocked_runable_queue;

import java.util.LinkedList;
import java.util.Queue;

/**
 * The class for queue of tasks (every task having type of Runnable).
 * Every task will be running of new own Thread.
 * The queue starts after initialisations and it all the times will be worked,
 * while application will be worked.
 * If the queue is empty, then it will wait tasks.
 */
public class BlockedRunnableQueue {

    /**The storage for task*/
    private final Queue<Runnable> tasks = new LinkedList<>();

    /**
     * The empty constructor, starts the queue, and starts the tasks.
     */
    public BlockedRunnableQueue() {
        new Thread(() -> {
            while (true) new Thread(getTask()).start();
        }).start();
    }

    /**
     * Method returns with deletes the first task in the storage or waits if it is empty.
     * @return the task for starts.
     */
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

    /**
     * Method for adds task in the queue.
     * @param task the input task(Runnable)
     */
    public synchronized void putTask(Runnable task) {
        tasks.add(task);
        notify();
    }
}