import java.io.*;
import java.util.*;

/**
 * Keep track of which distict words appear on which lines of a file.
 */
public class Index {
    // TODO: Fields
    List<Set<String>> distinctWordsWithLine;
    /**
     * Create an Index for the file named `filename`.  Words are separated by whitespace and are
     * case-sensitive.
     */
    public Set<String> Index(String filename) throws IOException {
        // TODO: Initialize fields
        distinctWordsWithLine = new ArrayList<>();

        // Read each line
        try (Reader in = new FileReader(filename)) {
            Scanner lines = new Scanner(in);
            while (lines.hasNextLine()) {
                Set<String> distinctWords = new HashSet<>();
                String line = lines.nextLine();

                // Process each word on the line (separated by whitespace)
                Scanner tokens = new Scanner(line);
                while (tokens.hasNext()) {
                    String word = tokens.next();



                }

                // TODO: End-of-line actions
            }
        }
    }

    /**
     * Creates a set of the distinct words on line number `lineNum` in the file that was indexed.
     * The first line of the file has line number 1.  Requires {@code lineNum >= 1}.  Throws
     * `IndexOutOfBoundsException` if `lineNum` exceeds the number of lines in the file.
     */
    public Set<String> wordsOnLine(int lineNum) {


    }

    /**
     * Creates a list of the line numbers on which `word` occurs in the file that was indexed.  The
     * first line of the file has line number 1.  The returned line numbers are in ascending order.
     */
    public List<Integer> linesWithWord(String word) {


    }

    public static void main(String[] args) throws IOException {
        Index index = new Index("Austen.txt");

        // TODO: How many distinct words are on line 18?

        // TODO: Does line 13 contain the word "young"?

        // TODO: How many lines does "with" appear on?
    }
}
