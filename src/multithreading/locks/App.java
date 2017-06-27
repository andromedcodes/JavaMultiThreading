package multithreading.locks;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by Andromed.Codes on 27/06/2017.
 * This Class demonstrates how to implement a lock object to synchronize access to thread class
 */
public class App {

    private Random random = new Random();

    private ArrayList<Integer> list1 = new ArrayList<>();
    private ArrayList<Integer> list2 = new ArrayList<>();

    public void main() {
        System.out.println("Starting ...");
        long start = System.currentTimeMillis();
        // Creating two worker Threads
        Thread t1 = new Thread(this::processor);
        Thread t2 = new Thread(this::processor);

        // Starting Threads and join
        t1.start();
        t2.start();
        try {
            t1.join();
            t2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        long end = System.currentTimeMillis();
        System.out.println("Elapsed time is : " + (end - start));
        System.out.println("List 1 has : " + list1.size());
        System.out.println("List 2 has : " + list2.size());
    }

    private void processor() {
        for (int i = 0; i < 1000; i++) {
            stage1();
            stage2();
        }
    }

    private void stage1() {
        synchronized (list1) {
            // Synchronize access to list1
            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        list1.add(random.nextInt(100));
    }

    private void stage2() {
        synchronized (list2) {
            // Synchronize access to list2
            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        list2.add(random.nextInt(100));
    }
}
