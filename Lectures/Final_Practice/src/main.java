
class Counter {
    private int count = 0;

        public void increment() throws InterruptedException {
            synchronized (this) {
                count++;
                System.out.println(count);
                notify();
                wait();
                System.out.println("Count was just decremented");
                System.out.println(count);
            }
        }

        public void decrement() throws InterruptedException {
            synchronized (this) {
                count-=2;
                notify();
                wait();

            }
        }

    public int getCount() {
        return this.count;
    }
}

public class main {

    public static void main(String[] args) throws InterruptedException {


        Counter counter = new Counter();
        Runnable task = () -> {
            for (int i = 100; i <1000; i++) {
                try {
                    counter.increment();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        };
        Runnable task2 = () -> {
            for (int i = 0; i < 1000; i++) {
                try {
                    counter.decrement();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        };


        Thread thread1 = new Thread(task);
        Thread thread2 = new Thread(task2);
        thread1.start();
        thread2.start();

        thread1.join();
        thread2.join();

        System.out.println(counter.getCount());


    }
}
