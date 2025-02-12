import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.Scanner;

/**
 * An application to count the number of tokens in a file.
 */
public class CountTokens {
    /**
     * Return the number of tokens in the file at path `filename`, where tokens are delimited by
     * `delimiter`.  Empty leading and trailing tokens are not counted.  If any exceptions are
     * thrown while opening, closing, or reading from the file, they are propagated to the caller.
     */
    public static int countTokens(String filename, String delimiter) throws IOException {
        int tokenCount = 0;
        try (Reader in = new FileReader(filename);
             Scanner lines = new Scanner(in)) {
            // Read each line in the file
            while (lines.hasNextLine()) {
                String line = lines.nextLine();

                // Count each token in the line
                Scanner tokens = (new Scanner(line)).useDelimiter(delimiter);
                while (tokens.hasNext()) {
                    String token = tokens.next();
                    tokenCount += 1;
                }
            }

            // Check for exceptions swallowed by lines Scanner
            if (lines.ioException() != null) {
                // Re-throw the exception that the Reader originally threw
                throw lines.ioException();
            }

            return tokenCount;
        }
        // No `catch` clause needed, because we propagate any exceptions to our caller.
        // But by using a try-with-resources statement, the lines Scanner and Reader are closed just
        // before returning or throwing (no need to close the tokens Scanner, since it's just
        // reading a String).
    }

    public static void main(String[] args) {
        String path = "hello.txt";
        String delimiter = " ";

        try {
            int tokenCount = countTokens(path, delimiter);
            System.out.println("Number of tokens in file " + path + ": " + tokenCount);
        } catch (IOException e) {
            System.err.println("Error reading from file " + path);
            System.exit(1);
            // Reminder: it is only appropriate to call `System.exit()` from `main()`, not from any
            // functions that might be called from other contexts.  Think of the exit code as the
            // "return value" of `main()`.
        }
    }
}
