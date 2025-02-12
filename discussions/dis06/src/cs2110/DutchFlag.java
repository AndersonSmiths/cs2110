package cs2110;

import java.util.Date;
import java.util.Random;

public class DutchFlag {
    /**
     * Rearrange values in `f` so that all "Red"s come first, followed by all
     * "White"s, followed by all "Blue"s.
     * Requires that each element of f is either "Red", "White", or "Blue".
     */
    static void dutchFlag(String[] f) {
        int i = 0; // red
        int j = i+1; // red
        int k = j+1; // blue

        while (k < f.length) {
            if (
        }



    }

    /** Swap elements at indices `i` and `j` in array `a`. */
    static void swap(String[] a, int i, int j) {
        String tmp = a[i];
        a[i] = a[j];
        a[j] = tmp;
    }

    public static void main(String[] args) {
        // Array of color choices (facilitates random selection)
        String[] rwb = new String[]{"Red", "White", "Blue"};

        // Random number generator (reporting and optionally setting seed
        // allows results to be reproduced)
        long seed;
        if (args.length > 0) {
            seed = Long.parseLong(args[0]);
        } else {
            seed = new Date().getTime();
            System.err.println("Seed: " + seed);
        }
        Random rng = new Random(seed);

        // Generate random array of colors
        String[] colors = new String[72];
        for (int i = 0; i < colors.length; ++i) {
            // Deliberately create new objects to flag bugs in equality tests
            colors[i] = new String(rwb[rng.nextInt(rwb.length)]);
        }

        // Run "Dutch National Flag" algorithm
        dutchFlag(colors);

        // Print results
        for (String s : colors) {
            char c = ' ';
            switch(s) {
                case "Red":
                    c = 'R'; break;
                case "White":
                    c = 'W'; break;
                case "Blue":
                    c = 'B'; break;
            }
            System.out.print(c);
        }
        System.out.println();
    }
}