import java.util.function.Consumer;

public class BenchmarkList {

    /**
     * Creates and returns a list of size `n`, with no duplicate elements.
     */
    private static List<Integer> makeList(int n) {
        List<Integer> list = new LinkedList<>();
        for (int i = 1; i < n + 1; i++) {
            list.addBack(i);
        }
        return list;
    }

    /**
     * Return the amount of time it takes to run `op` in nanoseconds.
     */
    public static double benchmarkOp(List<Integer> list, Consumer<List<Integer>> op) {
        double startTime = System.nanoTime();
        op.accept(list);
        double endTime = System.nanoTime();
        // Return milliseconds
        return (endTime - startTime) / 1.0e6;
    }

    /**
     * Runs frequencyOf() and addFront() on lists of size up to 3 million.
     * Note: hasDuplicates() was not included because it will take too much time.
     */
    public static void experiment1() {
        System.out.println("size,frequencyOf(),addFront()");
        for (int size = 0; size < 3000000; size += 100000) { // 3 million elements
            List<Integer> list = makeList(size);
            double addFrontTime = benchmarkOp(list, x -> x.addFront(0));
            double freqTime = benchmarkOp(list, x -> x.frequencyOf(0));
            System.out.println(size + "," + freqTime + "," + addFrontTime);
        }
    }

    /**
     * Runs hasDuplicates(), frequencyOf(), and addFront() on lists of size up to
     * 20 thousand.
     */
    public static void experiment2() {
        System.out.println("size,hasDuplicates(),frequencyOf(),addFront()");
        for (int size = 0; size < 20000; size += 1000) { // 3 million elements
            List<Integer> list = makeList(size);
            double hasDupsTime = benchmarkOp(list, x -> x.hasDuplicates());
            double addFrontTime = benchmarkOp(list, x -> x.addFront(0));
            double freqTime = benchmarkOp(list, x -> x.frequencyOf(0));
            System.out.println(size + "," + hasDupsTime + "," + freqTime + "," + addFrontTime);
        }
    }

    public static void main(String[] args) {
        // warmup
        List<Integer> warmup = makeList(10000);
        warmup.hasDuplicates();
        warmup.frequencyOf(0);
        warmup.addFront(0);

        experiment1(); // frequencyOf() and addFront() on lists up to 3 billion
//        experiment2(); // hasDuplicates(), frequencyOf() and addFront() on lists up to 20 thousand
    }

}
