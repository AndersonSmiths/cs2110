package cs2110;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;


/**
 * Test suite for functions in discussion activity 1.
 */
class Dis01Test {

    /**
     * Tests for `triangleArea()`.
     */
    @Nested
    class TriangleAreaTest {

        @Test
        @DisplayName("A triangle with no sides has no area")
        void testZeroArea() {
            assertEquals(0.0, Dis01.triangleArea(0.0, 0.0));
        }

        @Test
        @DisplayName("A 3-4-5 right triangle has area 6")
        void testIntegerArea() {
            assertEquals(6.0, Dis01.triangleArea(3.0, 4.0));
        }

        @Test
        @DisplayName("A 1-1-sqrt(2) right triangle has area 1/2")
        void testRationalArea() {
            assertEquals(0.5, Dis01.triangleArea(1.0, 1.0));
        }
    }

    /**
     * Tests for `celsiusToFahrenheit()`.
     */
    @Nested
    class CelsiusToFahrenheitTest {

        @Test
        @DisplayName("The freezing point of water (@ 1 atm) is at 0 degC = 32 degF")
        void testFreezing() {
            assertEquals(32.0, Dis01.celsiusToFahrenheit(0));
        }

        @Test
        @DisplayName("The boiling point of water (@ 1 atm) is at 100 degC = 212 degF")
        void testBoiling() {
            assertEquals(212.0, Dis01.celsiusToFahrenheit(100));
        }

        @Test
        @DisplayName("The Celsius and Fahrenheit scales match at -40 degC = -40 degF")
        void testIntersection() {
            assertEquals(-40.0, Dis01.celsiusToFahrenheit(-40.0));
        }
    }

    /**
     * Tests for `isLeapYear()`.
     */
    @Nested
    class IsLeapYearTest {

        @Test
        @DisplayName("2024 is a leap year")
        void testMult4() {
            assertTrue(Dis01.isLeapYear(2024));
        }

        @Test
        @DisplayName("2023 was not a leap year")
        void testNotMult4() {
            assertFalse(Dis01.isLeapYear(2023));
        }

        @Test
        @DisplayName("1900 was not a leap year")
        void testMult100() {
            assertFalse(Dis01.isLeapYear(1900));
        }

        @Test
        @DisplayName("2000 was a leap year")
        void testMult400() {
            assertTrue(Dis01.isLeapYear(2000));
        }
    }
}
