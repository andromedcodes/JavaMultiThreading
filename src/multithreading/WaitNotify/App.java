package multithreading.WaitNotify;

import java.util.Scanner;

/**
 * Created by Andromed.Codes on 27/06/2017.
 * this class demonstrates how to implement "Wait" and "Notify" mechanism.
 */
public class App {

    private Thread t1 = new Thread(() -> {
        try {
            producer();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    });

    private Thread t2 = new Thread(() -> {
        try {
            consumer();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    });

    public void execute() {
        t1.start();
        t2.start();
        try {
            t1.join();
            t2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Program completed!");
        // Finished all threads
        t1.interrupt();
        t2.interrupt();
    }

    private void producer() throws InterruptedException {
        synchronized (this) {
            System.out.println("Producer thread running ...");
            wait();
            System.out.println("Producer thread is resumed.");
        }
    }

    private void consumer() throws InterruptedException {
        Scanner scanner = new Scanner(System.in);
        Thread.sleep(2000);
        synchronized (this) {
            System.out.println("Producer thread is paused ...");
            System.out.println("Waiting for return key ...");
            scanner.nextLine();
            System.out.println("Return key pressed.");
            notify();
        }
    }
}
