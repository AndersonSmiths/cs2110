import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.Scanner;

/**
 * An application to sum the integer tokens in a file.  Tokens are delimited by commas.
 */
public class SumTokensDemo {
    // Propagate exceptions out of `main()`, crashing the program.
    // This is convenient for demos.
    public static void main(String[] args) throws IOException {
        String path = "ints.txt";
        int tokenSum = 0;

        // try-with-resources statement ensures reader is closed
        try (Reader in = new FileReader(path);
             Scanner lines = new Scanner(in)) {
            // Read each line in the file
            while (lines.hasNextLine()) {
                String line = lines.nextLine();

                // Sum each token in the line
                Scanner tokens = new Scanner(line);
                tokens.useDelimiter(",");
                while (tokens.hasNext()) {
                    int token = tokens.nextInt();
                    tokenSum += token;
                }
            }
        }

        System.out.println("Sum of tokens: " + tokenSum);
    }
}
