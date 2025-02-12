package cs2110;


/**
 * Collection of misc. static functions for showcasing the capabilities of Java in a procedural
 * context.
 */
public class A1 {

    /**
     * Return the area of a regular polygon with `nSides` sides of length `sideLength`. Units of
     * result are the square of the units of `sideLength`. Requires `nSides` is at least 3,
     * `sideLength` is non-negative.
     */
    public static double polygonArea(int nSides, double sideLength) {

        // calculation of polygon area using given formula
        double a = Math.tan(Math.PI/nSides); //a used because Java b/c of Javadoc example
        double area = (1.0/4.0 * (Math.pow(sideLength, 2.0))*(nSides / a));
        return area;

    }

    /**
     * Return the next term in the Collatz sequence after the argument.  If the argument is even,
     * the next term is it divided by 2.  If the argument is odd, the next term is 3 times it plus
     * 1.  Requires magnitude of odd `x` is less than `Integer.MAX_VALUE/3` (otherwise overflow is
     * possible).
     */

    public static int nextCollatz(int s){
        if (s % 2 == 0){
            return s / 2;
        }
        else {
            return s * 3 + 1;
        }
    }


    /**
     * Return the sum of the Collatz sequence starting at `seed` and ending at 1 (inclusive).
     * Requires `seed` is positive, sum does not overflow.
     */
    public static int collatzSum(int seed) {
        // Implementation constraint: Use a while-loop.  Call `nextCollatz()` to
        // advance the sequence.
        int sum = 0;
        while (seed != 1){
            sum += seed;
            seed = nextCollatz(seed);
        }
        // adds 1 to sum because of inclusivity
        sum += 1;
        return sum;

    }



    /**
     * Return the median value among `{a, b, c}`.  The median has the property that at least half of
     * the elements are less than or equal to it and at least half of the elements are greater than
     * or equal to it.
     */
    public static int med3(int a, int b, int c) {
        // Implementation constraint: Do not call any other methods.
        if (a >= b && a <= c || a >= c && a <= b) {
            return a;
        }
        else if ((b >= a && b <= c) || (b >= c && b <= a)){
            return b;
        }
        else {
            return c;
        }

    }

    /**
     * Return whether the closed intervals `[lo1, hi1]` and `[lo2, hi2]` overlap.  Two intervals
     * overlap if there exists a number contained in both of them.  Notation: the interval `[lo,
     * hi]` contains all numbers greater than or equal to `lo` and less than or equal to `hi`.
     * Requires `lo1` is less than or equal to `hi1` and `lo2` is less than or equal to `hi2`.
     */
    public static boolean intervalsOverlap(int lo1, int hi1, int lo2, int hi2) {
        // Implementation constraint: Use a single return statement to return
        // the value of a Boolean expression; do not use an if-statement.

        return ((hi1 - lo2) >= 0) && ((hi2 - lo1 >= 0));

    }

    /**
     * Return the approximation of pi computed from the sum of the first `nTerms` terms of the
     * Madhava-Leibniz series.  This formula states that pi/4 = 1 - 1/3 + 1/5 - 1/7 + 1/9 - ...
     * Requires `nTerms` is non-negative.
     */
    public static double estimatePi(int nTerms) {
        // Implementation constraint: Use a for-loop.  Do not call any other
        // methods (including `Math.pow()`).

        double aproxPi = 0.0;

        for (int i = 0; i < nTerms; i++){

            // conditional to aid in alternation of signs
            if (i % 2 != 0) {
                aproxPi += -1.0 / (2.0 * i + 1.0);
            }
            else {
                aproxPi += 1.0 / (2.0 * i + 1.0);
            }

        } // end of for loop

        return 4 * aproxPi;


    }

    /**
     * Returns whether the sequence of characters in `s` is equal (case-sensitive) to that sequence
     * in reverse order.
     */
    public static boolean isPalindrome(String s) {
        // Implementation constraint: Use the `charAt()` and `length()` methods
        // of the `String` class.

        int first = 0;
        int last = s.length() -1;

        // while loop to check every both halves of string against each other
        // previously implemented with nested for loops but changed to while
        // to decrease O(n)
        while (last > first){
            /**
             * Question: original method of implementation was to create an empty string
             * and use a for loop to add all characters of s to said string in reverse
             * order. This is how I have historically done so in python, but this does
             * not work in java. Why does this not work even though the + operator works
             * in java?
             */
            if (s.charAt(first) != s.charAt(last)) {
                return false;
            }
            first+=1;
            last-=1;
        }
        return true;
    }

    /**
     * Return an order confirmation message in English containing the order ID and the number of
     * items it contains.  Message shall handle item plurality properly (e.g. "1 item" vs. "3
     * items") and shall surround the order ID in single quotes. Examples:
     * <pre>
     * formatConfirmation("123ABC", 1) should return
     *   "Order '123ABC' contains 1 item."
     * formatConfirmation("XYZ-999", 3)" should return
     *   "Order 'XYZ-999' contains 3 items."
     * </pre>
     * Requires `orderId` only contains digits, hyphens, or letters 'A' - 'Z'; `itemCount` is
     * non-negative.
     */
    public static String formatConfirmation(String orderId, int itemCount) {
        // Implementation constraint: Use Java's ternary operator (`?:`) to give "item" the
        // appropriate plurality.
        String pluralItem = (itemCount == 1) ? "item" : "items";
        return "Order '" + orderId + "' contains " + itemCount + " " + pluralItem + ".";
    }

    /**
     * Computes the polygon area of 3 different polygons. Then, the polygon areas
     * are casted into ints and their corresponding collatz sums are calculated.
     * The median of these numbers is calculated using med3, and this number
     * is used as the orderId in the function fromatConfirmation. nSides of polygon 2
     * is used as the itemCount in the formatConfirmation function;
     * the return statement of this function is printed.
     */
    public static void main(String[] args){
        // polygon 1
        int p1Sides = 5;
        double p1SideLength = 6.2;

        // polygon 2
        int p2Sides = 9;
        double p2SideLength = 12.2;

        //polygon 3
        int p3Sides = 13;
        double p3SideLength = 2.1;

        // Polygon area calculations/variable assignment
        double p1Area = polygonArea(p1Sides, p1SideLength);
        double p2Area = polygonArea(p2Sides, p2SideLength);
        double p3Area = polygonArea(p3Sides, p3SideLength);

        // collatz sums of polygons
        int cSum1 = collatzSum((int) p1Area);
        int cSum2 = collatzSum((int) p2Area);
        int cSum3 = collatzSum((int) p3Area);

        // median of collatz sums
        int median = med3(cSum1, cSum2, cSum3);

        // implementation of median/polygon2 nSides into formatConfirmation
        // used information from the documentation of the Integer Class
        // https://docs.oracle.com/en/java/javase/21/docs/api/java.base/java/lang/Integer.html#toString(int)
        String order = formatConfirmation(Integer.toString(median), p2Sides);

        // printing of the order
        System.out.println(order);

    }
}
