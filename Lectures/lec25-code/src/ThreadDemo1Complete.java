public class ThreadDemo1Complete {

    static void task1() {
        for (int i = 0; i < 5; ++i) {
            randomPause();
            System.out.println("1: Hi ho! Hi ho!");
        }
        System.out.println("Done task1");
    }

    static void task2(String place) {
        for (int i = 0; i < 6; ++i) {
            randomPause();
            System.out.println("2: It's off to " + place + " we go!");
        }
        System.out.println("Done task2");
    }

    public static void main(String[] args) throws InterruptedException {
        // Create object on main thread
        String place = "work";

        // Create additional thread and share `place` object
        Thread t = new Thread(() -> task2(place));
        // start the thread
        t.start();

        // Work on task1 in main thread
        task1();

        // Wait for task2 thread to finish (blocking)
        // TODO: try out with/without waiting. What's the difference?
        t.join();

        // Main thread is done, but that doesn't mean the program exits right away
        System.out.println("Done main");
    }

    /**
     * Pause thread for a random duration between 1/4 s and 1 s.
     */
    static void randomPause() {
        try {
            Thread.sleep((int) (750 * Math.random()) + 250);
        } catch (InterruptedException e) {
            // FIXME: This is only appropriate for demo purposes
            throw new RuntimeException(e);
        }
    }
}