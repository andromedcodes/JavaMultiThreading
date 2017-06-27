import java.util.Scanner;

/**
 * Created by Andromed.Codes on 27/06/2017.
 */
public class EntryPoint {
    public static void main(String[] args) {
        multithreading.synchronizedmethod.App app1 = new multithreading.synchronizedmethod.App();
        multithreading.locks.App app2 = new multithreading.locks.App();
        multithreading.countdownlatches.App app3 = new multithreading.countdownlatches.App();

        boolean isRunning = true;

        final String help =
                "1 : Synchronized methods\n" +
                        "2 : Locks\n" +
                        "3 : Countdown Latches\n" +
                        "4 : Task Queues\n" +
                        "exit : Quit program";

        while (isRunning) {
            System.out.println("Which Program do you want to execute?");
            Scanner mScanner = new Scanner(System.in);
            switch (mScanner.next()) {
                case "exit":
                    System.out.println("See you next time!! ;)");
                    isRunning = false;
                    break;
                case "help":
                    System.out.println(help);
                    break;
                case "1":
                    System.out.println("Starting Demo 1 : Synchronized methods");
                    app1.main();
                    break;
                case "2":
                    System.out.println("Starting Demo 2 : Locks");
                    app2.main();
                    break;
                case "3":
                    System.out.println("Starting Demo 3 : Countdown Latches");
                    app3.main();
                    break;
                case "4":
                    System.out.println("Starting Demo 4 : Task Queues");
                    break;
                default:
                    System.out.println("Please Provide a valid program index!!");
                    System.out.println("Hint : Type 'help' to print the demos index");
                    break;
            }
        }
    }
}
