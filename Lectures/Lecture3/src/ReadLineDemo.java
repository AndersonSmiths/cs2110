import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.Reader;
import java.util.Scanner;

public class ReadLineDemo {

    /**
     *
     *  Print all lines from the text file "hello.txt"
     */

    public static void main(String[] args) {
        String path = "hello.txt";


        // auto recommended try/catch
        try {
            Reader in = new FileReader(path);
            Scanner scanner = new Scanner(in);

            while (scanner.hasNextLine()){
                String line = scanner.nextLine();
                System.out.println(line);
            }

        } catch (FileNotFoundException ex) {
            System.err.println("Error opening file " + path);
            System.exit(1);
        }







    } // main
} // class
