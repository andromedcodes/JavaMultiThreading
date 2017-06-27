package multithreading.countdownlatches;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by Andromed.Codes on 27/06/2017.
 */
public class App {

    public void execute() {
        int threadsNbr = 3;
        // Defining an Executor that has 3 worker threads
        ExecutorService executor = Executors.newFixedThreadPool(threadsNbr);
        // Defining a countdown latch of three units
        CountDownLatch latch = new CountDownLatch(3);

        for (int i = 0; i < threadsNbr; i++)
            // Assigning work to threads
            executor.submit(new Processor(latch));

        // Awaiting all threads to finish
        try {
            latch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("Completed ...");
    }

    class Processor implements Runnable {

        private CountDownLatch latch;

        Processor(CountDownLatch latch) {
            this.latch = latch;
        }

        @Override
        public void run() {
            System.out.println("Started ...");
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            // Countdown the latch
            latch.countDown();
        }
    }
}
