import java.util.Random;

class Tortoise implements Runnable {

    @Override
    public void run() {
        int distance = 0;
        Random rng = new Random();

        System.out.println("Tortoise starts");
        while (distance < 50) {
            // Run 5 m every 1 s
            FableDemo.delay(rng.nextInt(990, 1010));
            distance += 5;
            System.out.println("Tortoise is at the " + distance + " m line");
        }
        System.out.println("Tortoise finishes");
    }
}

class Hare implements Runnable {

    @Override
    public void run() {
        int distance = 0;
        Random rng = new Random();

        System.out.println("\t\tHare starts");
        while (distance < 50) {
            // Run 10 m every ~1 s
            FableDemo.delay(rng.nextInt(900, 1100));
            distance += 10;
            System.out.println("\t\tHare is at the " + distance + " m line");

            // Chance to take a nap
            if (distance < 50 && rng.nextBoolean()) {
                System.out.println("\t\tHare is taking a nap");
                int napTime = rng.nextInt(2000, 3000);
                FableDemo.delay(napTime);
                System.out.println("\t\tHare woke up after " + napTime + " ms");
            }
        }
        System.out.println("\t\tHare finishes");
    }
}

public class FableDemo {
    public static void main(String[] args) {
        Thread tortoiseThread = new Thread(new Tortoise());
        Thread hareThread = new Thread(new Hare());

        System.out.println("Ready? Set? Go!");
        tortoiseThread.start();
        hareThread.start();
        System.out.println("My job is done");
    }

    /**
     * Have current thread sleep for `duration` ms.  Throws RuntimeException if thread is
     * interrupted.
     */
    static void delay(int duration) {
        try {
            Thread.sleep(duration);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
