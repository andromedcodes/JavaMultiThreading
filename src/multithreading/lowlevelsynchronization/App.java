package multithreading.lowlevelsynchronization;

import java.util.LinkedList;
import java.util.Random;
import java.util.Scanner;

/**
 * Created by Andromed.Codes on 27/06/2017.
 * This class demonstrates how to implement a low-level synchronizations on objects.
 * the main goal of this class is to manage read/write access to an object by multiple threads
 */
public class App {

    private final Object mLock = new Object();
    private LinkedList<Integer> mList = new LinkedList<>();
    private boolean stopRequest = false;
    private Thread t1 = new Thread(this::append);
    private Thread t2 = new Thread(() -> {
        try {
            shrink();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    });


    public void execute() {
        t1.start();
        t2.start();

        Scanner scanner = new Scanner(System.in);
        if (scanner.nextLine().equals(""))
            stopRequest = true;

        try {
            t1.join();
            t2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void append() {
        Random random = new Random();
        while (!stopRequest)
            synchronized (mLock) {
                mList.add(random.nextInt(1000));
                mLock.notify();
            }
    }

    private void shrink() throws InterruptedException {
        while (!stopRequest) {
            synchronized (mLock) {
                while (mList.size() == 0)
                    try {
                        mLock.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                int value = mList.removeFirst();
                System.out.println(value + " was removed from the list.");
                mLock.notify();
            }
            Thread.sleep(1000);
        }
    }
}
