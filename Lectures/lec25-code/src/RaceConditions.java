import java.util.LinkedList;
import java.util.List;
import java.util.Random;

class SharedInt {
    int x;
    SharedInt() {
        x = 0;
    }
}

public class RaceConditions {
    public static void main(String[] args) throws InterruptedException {
        int nThreads = 16;
        int nIterations = 1000;

        // Create shared mutable variable
        SharedInt shared = new SharedInt();

        // Create threads and define their tasks
        List<Thread> threads = new LinkedList<>();
        for (int i = 0; i < nThreads; ++i) {
            // Task defined with lambda expression that _captures_ `shared`
            threads.add(new Thread(() -> {
                for (int k = 0; k < nIterations; ++k) {
                    // Hack to prevent compiler from replacing loop with final answer
                    // inc will always be 1
                    int inc = new Random().nextInt(10)/10 + 1;
                    shared.x += inc;
                    System.out.println(shared.x);
                }
            }));
        }
        int k = 0;
        // Start threads
        for (Thread t : threads) {
            System.out.println("starting thread");
            k++;
            t.start();

        }

        // Wait for all threads to finish
        for (Thread t : threads) {
            t.join();
            System.out.println("thread" + k + "finished");
            k++;
        }

        System.out.println("Final value of shared.x: " + shared.x);
    }
}