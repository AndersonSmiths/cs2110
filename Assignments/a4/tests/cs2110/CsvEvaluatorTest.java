package cs2110;

import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;
import java.io.StringReader;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.QuoteMode;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class CsvEvaluatorTest {

    @Test
    @DisplayName("The column label for column 0 should be the empty string")
    void testColToLetters0() {
        assertEquals("", CsvEvaluator.colToLetters(0));
    }

    @Test
    @DisplayName("Column labels for columns 1-26 should consist of the correct single letter")
    void testColToLetters1() {
        assertEquals("A", CsvEvaluator.colToLetters(1));
        assertEquals("Z", CsvEvaluator.colToLetters(26));
    }

    @Test
    @DisplayName("Column labels for columns 27-704 should consist of the correct two letters")
    void testColToLetters2() {
        assertEquals("AA", CsvEvaluator.colToLetters(27));
        assertEquals("AB", CsvEvaluator.colToLetters(28));
        assertEquals("ZY", CsvEvaluator.colToLetters(701));
        assertEquals("ZZ", CsvEvaluator.colToLetters(702));
    }

    @Test
    @DisplayName("Column labels for columns 703-18278 should consist of the correct three letters")
    void testColToLetters3() {
        assertEquals("AAA", CsvEvaluator.colToLetters(703));
        assertEquals("AAB", CsvEvaluator.colToLetters(704));
        assertEquals("AMJ", CsvEvaluator.colToLetters(1024));
        assertEquals("XFD", CsvEvaluator.colToLetters(16384));
        assertEquals("ZZY", CsvEvaluator.colToLetters(18277));
        assertEquals("ZZZ", CsvEvaluator.colToLetters(18278));
    }



    @Test
    @DisplayName("A spreadsheet containing only constants should not be modified when evaluating " +
            "its formulas")
    void testEvaluateCsvConstant() throws IOException {
        String input = "x,1.5\n";
        String expected = "x,1.5\n";

        StringBuilder output = new StringBuilder();
        CsvEvaluator.evaluateCsv(CsvEvaluator.SIMPLIFIED_CSV.parse(new StringReader(input)),
                CsvEvaluator.SIMPLIFIED_CSV.print(output));
        assertEquals(expected, output.toString());
    }

    @Test
    @DisplayName("A spreadsheet with a formula referencing a cell on a previous row should " +
            "evaluate correctly.")
    void testEvaluateCsvAboveRef() throws IOException {
        String input = "x,1.5\n" +
                "y,=B1 4 * 1 +\n";
        String expected = "x,1.5\n"
                + "y,7.0\n";

        StringBuilder output = new StringBuilder();
        CsvEvaluator.evaluateCsv(CsvEvaluator.SIMPLIFIED_CSV.parse(new StringReader(input)),
                CsvEvaluator.SIMPLIFIED_CSV.print(output));
        assertEquals(expected, output.toString());
    }

    @Test
    @DisplayName("A spreadsheet with a formula referencing a previous cell on the same row " +
            "should evaluate correctly.")
    void testEvaluateCsvLeftRef() throws IOException {
        String input = "x,1.5,=B1 4 * 1 +\n";
        String expected = "x,1.5,7.0\n";

        StringBuilder output = new StringBuilder();
        CsvEvaluator.evaluateCsv(CsvEvaluator.SIMPLIFIED_CSV.parse(new StringReader(input)),
                CsvEvaluator.SIMPLIFIED_CSV.print(output));
        assertEquals(expected, output.toString());
    }

    @Test
    @DisplayName("A spreadsheet with a formula referencing a previous formula should evaluate " +
            "correctly.")
    void testEvaluateCsvFormulaRef() throws IOException {
        String input = "x,1.5\n" +
                "y,=B1 4 * 1 +\n" +
                "z,=B1 B2 *\n";
        String expected = "x,1.5\n"
                + "y,7.0\n"
                + "z,10.5\n";

        StringBuilder output = new StringBuilder();
        CsvEvaluator.evaluateCsv(CsvEvaluator.SIMPLIFIED_CSV.parse(new StringReader(input)),
                CsvEvaluator.SIMPLIFIED_CSV.print(output));
        assertEquals(expected, output.toString());
    }

    @Test
    @DisplayName("A spreadsheet formula referencing a cell that does not contain a number should " +
            "evaluate to #N/A.")
    void testEvaluateCsvNonNumericRef() throws IOException {
        String input = "x,1.5\n" +
                "w,=A1\n";
        String expected = "x,1.5\n"
                + "w,#N/A\n";

        StringBuilder output = new StringBuilder();
        CsvEvaluator.evaluateCsv(CsvEvaluator.SIMPLIFIED_CSV.parse(new StringReader(input)),
                CsvEvaluator.SIMPLIFIED_CSV.print(output));
        assertEquals(expected, output.toString());
    }

    @DisplayName("Formulas with out of bounds cell references should evaluate to #N/A")
    @Test
    void testEvaluateOutOfBounds() throws IOException {
        String input = "a,2.5\n" +
                "b,3.5\n" +
                "c,=B5 2 *\n" +
                "d,=A4 3 +\n";

        String expected = "a,2.5\n" +
                "b,3.5\n" +
                "c,#N/A\n" +
                "d,#N/A\n";

        StringBuilder output = new StringBuilder();
        CsvEvaluator.evaluateCsv(CsvEvaluator.SIMPLIFIED_CSV.parse(new StringReader(input)),
               CsvEvaluator.SIMPLIFIED_CSV.print(output));
        assertEquals(expected, output.toString());
    }

    @DisplayName("Attempting to evaluate an unknown formula will result in #N/A")
    @Test
    void testEvaluateUnknownFunction() throws IOException {
        String input = "a,5.5\n" +
                "b,6.5\n" +
                "c,=unknown(2 3 +)\n"; // Applying an unknown function

        String expected = "a,5.5\n" +
                "b,6.5\n" +
                "c,#N/A\n";

        StringBuilder output = new StringBuilder();
        CsvEvaluator.evaluateCsv(CsvEvaluator.SIMPLIFIED_CSV.parse(new StringReader(input)),
                CsvEvaluator.SIMPLIFIED_CSV.print(output));
        assertEquals(expected, output.toString());
    }

    @DisplayName("Attempting to evaluate a formula with variables that do not " +
            "correspond to a cells coordinate should result in #N/A")
    @Test
    void testNonCorrespondingVariable() throws IOException{
        String input = "a,5.5\n" +
                "b,3.7\n" +
                "c,=i j +\n";

        String expected = "a,5.5\n" +
                "b,3.7\n" +
                "c,#N/A\n";

        StringBuilder output = new StringBuilder();
        CsvEvaluator.evaluateCsv(CsvEvaluator.SIMPLIFIED_CSV.parse(new StringReader(input)),
                CsvEvaluator.SIMPLIFIED_CSV.print(output));
        assertEquals(expected, output.toString());
    }

    @DisplayName("Attempting to reference future cells should result in #N/a")
    @Test
    void testFutureCells() throws  IOException {
        String input = "a,2.5\n" +
                "b,3.7\n" +
                "c,=B20 2 *\n";


        String expected = "a,2.5\n" +
                "b,3.7\n" +
                "c,#N/A\n";


        StringBuilder output = new StringBuilder();
        CsvEvaluator.evaluateCsv(CsvEvaluator.SIMPLIFIED_CSV.parse(new StringReader(input)),
                CsvEvaluator.SIMPLIFIED_CSV.print(output));
        assertEquals(expected, output.toString());

    }

    @DisplayName("Attempting to use formulas containing incomplete RPN expression" +
            " should result in #N/a")
    @Test
    void testIncompleteRPN() throws  IOException {
        String input = "a,5.5\n" +
                "b,=5 +\n";

        String expected = "a,5.5\n" +
                "b,#N/A\n";


        StringBuilder output = new StringBuilder();
        CsvEvaluator.evaluateCsv(CsvEvaluator.SIMPLIFIED_CSV.parse(new StringReader(input)),
                CsvEvaluator.SIMPLIFIED_CSV.print(output));
        assertEquals(expected, output.toString());
    }

    // Not yet tested:
    // * Formulas with known function applications: correct evaluation DONE
    // * Formulas with unknown function applications: #N/A DONE
    // * Formulas with future cell references (to numbers or other formulas): #N/A DONE
    // * Formulas with out-of-bounds cell references: #N/A DONE
    // * Formulas with variables that do not correspond to a cell coordinate: #N/A DONE
    // * Formulas containing an incomplete RPN expression: #N/A DONE
    //
    // TODO: The autograder will test your code under these conditions.  It is up to you to decide
    // whether the given tests provide sufficient coverage or whether you should write more to boost
    // your confidence in your code's behavior.


}
