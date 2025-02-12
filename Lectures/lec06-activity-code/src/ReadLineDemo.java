import java.io.Reader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;

/**
 * Read all lines using a `Scanner`.
 */
public class ReadLineDemo {
    public static void main(String[] args) {
        String path = "hello.txt";
        try {
            Reader in = new FileReader(path);
            Scanner sc = new Scanner(in);
            while (sc.hasNextLine()) {
                String line = sc.nextLine();
                System.out.println(line);
            }
        } catch (FileNotFoundException e) {
            System.err.println("Error opening file " + path);
            System.exit(1);
        }
        // FIXME: File is never closed
    }
}
