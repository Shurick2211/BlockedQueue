import onimko.blocked_runable_queue.BlockedRunnableQueue;

import static java.lang.Thread.sleep;

/**
 * Test for BlockedRunnableQueue.
 */
public class Test {
    /**Time of start*/
    static long startTime = System.currentTimeMillis();

    /**Number of task*/
    static int count = 0;

    /**
     * Start method
     * @param args - the array of args
     */
    public static void main(String[] args) {
        BlockedRunnableQueue tasks = new BlockedRunnableQueue();
        for (int j = 0; j < 2; j++) {
            for (int i = 0; i < 5; i++) {
                tasks.putTask(someTask());
                try {
                    sleep(500);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
            try {
                sleep(5000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        System.exit(1);
    }

    /**
     * Method creates a some task.
     * @return new Runnable (the task for test)
     */
    private static Runnable  someTask(){
        count++;
        return () -> {
            int  number = count;
            System.out.println("Task №" + number + " starts. Time for start:  "
                    + (System.currentTimeMillis() - startTime)
                    + " : " + Thread.currentThread().getName());
            try {
                sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("Task №" + number + " stop!");
        };
    }
}