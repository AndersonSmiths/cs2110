package cs2110;

/**
 * Demo functions and application for discussion activity 1.
 */
public class Dis01 {

    /**
     * Return the area of a triangle whose base has length `baseLength` and whose orthogonal height
     * has length `height`.
     */
    static double triangleArea(double baseLength, double height) {
        // FIXME: this implementation is buggy

        return 0.5 * baseLength * height;
    }

    /**
     * Return the temperature represented by `tempC` (given in degrees Celsius) converted to degrees
     * Fahrenheit.
     */
    static double celsiusToFahrenheit(double tempC) {
        // FIXME: this implementation is buggy

        double scale = 9.0 / 5.0;  // degF / degC
        double offset = 32.0;  // degF
        double tempF;
        tempF = scale * tempC + offset;
        return tempF;
    }

    /**
     * Return whether `year` is a leap year in the Gregorian calendar.
     */
    static boolean isLeapYear(int year) {
        // FIXME: this implementation is buggy

        // Determine whether `year` is a multiple of 4, 100, and 400
        boolean isMult4 = year % 4 == 0;
        boolean isMult100 = year % 100 == 0;
        boolean isMult400 = year % 400 == 0;

        // Years divisible by 4 are leap years unless they are also divisible
        // by 100 but not 400.
        return isMult4 && (!isMult100 || isMult400);
    }

    /**
     * Running this application will print the conversion of 15 degrees Celsius to degrees
     * Fahrenheit.  (Program arguments are ignored.)
     */
    public static void main(String[] args) {
        String s2 = "abc" + 1 + 2;
        System.out.println(s2);
        double myTempC = 15.0;
        double myTempF = celsiusToFahrenheit(myTempC);
        System.out.println("My program says that " + myTempC + " degC = " + myTempF + " degF.");
    }
}
