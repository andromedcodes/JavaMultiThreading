package multithreading.queues;

import java.util.Random;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/**
 * Created by Andromed.Codes on 27/06/2017.
 * This Class Demonstrates how blocking queues works
 * Queues are useful to hand tasks to worker threads, whenever a thread is free to take new ones.
 * In this code snippet, a producer-consumer pattern is implemented.
 */
public class App {

    private BlockingQueue<Integer> blockingQueue = new ArrayBlockingQueue<>(10);

    public void execute() {
        Thread t1 = new Thread(() -> {
            try {
                producer();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        Thread t2 = new Thread(() -> {
            try {
                consumer();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        t1.start();
        t2.start();
        try {
            t1.join();
            t2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Demo completed ...");
        // Finishing threads
        t1.interrupt();
        t2.interrupt();
    }

    private void producer() throws InterruptedException {
        Random random = new Random();
        while (blockingQueue.size() < 10)
            blockingQueue.put(random.nextInt(100));
    }

    private void consumer() throws InterruptedException {
        Random random = new Random();
        while (blockingQueue.size() > 0) {
            Thread.sleep(100);
            if (random.nextInt(10) == 0) {
                Integer value = blockingQueue.take();
                System.out.println("Taken value is : " + value + " Remaining queue size is : " + blockingQueue.size());
            }
        }
    }
}
