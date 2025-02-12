import java.io.*;
import java.sql.SQLOutput;
import java.util.*;

public class WordHunt {
    /**
     * Read the 37th entry on the 13th line of file "index.txt", then split that
     * entry into a filename prefix, line number, and word number, and print the
     * corresponding word in that ".txt" file.
     */
    public static void main(String[] args) {

        String path = "index.txt";


        int lineNumber = 0;
        int lineNumber2 = 0;
        try {
            Reader in = new FileReader(path);
            Scanner sc = new Scanner(in);
            while (sc.hasNextLine()){
                String line = sc.nextLine();
                if (lineNumber == 12){
                    String[] entries = line.split(" ");
                    String entry = entries[36]; // 37th entry on 13th line

                    // opening enw file
                    String[] pathName = entry.split("-");
                    String path2 = pathName[0];
                    String lineTwo = pathName[1];

                    Reader inTwo = new FileReader(path2 + ".txt");
                    Scanner scanner = new Scanner(inTwo);




                }

                lineNumber++;
            }

            while(lineNumber2 < )


        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }




    }

    // TODO: Consider defining a static helper method if you need to perform the
    //       same non-trivial task more than once.
}
