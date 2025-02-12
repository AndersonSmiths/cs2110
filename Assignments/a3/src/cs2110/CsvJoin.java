package cs2110;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class CsvJoin {

    /**
     * Load a table from a Simplified CSV file and return a row-major list-of-lists representation.
     * The CSV file is assumed to be in the platform's default encoding. Throws an IOException if
     * there is a problem reading the file.
     */
    public static Seq<Seq<String>> csvToList(String file) throws IOException {


        Seq<Seq<String>> table = new LinkedSeq<>();

        try {
            FileReader in = new FileReader(file);
            Scanner sc = new Scanner(in);
            while (sc.hasNextLine()) {
                String line = sc.nextLine();
                String[] values = line.split(",", -1);

                // adding rows to a new seq so said seq can be added to table
                // row because row-major list-of-lists
                Seq<String> row = new LinkedSeq<>();

                for (String value : values) {
                    row.append(value);
                }
                table.append(row);

            }
        }

        catch (FileNotFoundException e) {
            throw new IOException(e);
        }
        return table;
    }

    /**
     * Return the left outer join of tables `left` and `right`, joined on their first column. Result
     * will represent a rectangular table, with empty strings filling in any columns from `right`
     * when there is no match. Requires that `left` and `right` represent rectangular tables with at
     * least 1 column.
     */
    public static Seq<Seq<String>> join(Seq<Seq<String>> left, Seq<Seq<String>> right) {

        // asssertions
        assert left.size() >= 1 && right.size() >= 1;

        // handle non-rectangluar
        boolean isRectangular = true;
        int lcols = left.get(0).size();
        int rcols = right.get(0).size();

        for (Seq<String> row : left) {
            if (row.size() != lcols) {
                isRectangular = false;
            }
        }

        for (Seq<String> row : right) {
            if (row.size() != rcols) {
                isRectangular = false;
            }
        }

        assert isRectangular;


        // joining of tables
        Seq<Seq<String>> joined = new LinkedSeq<>();


        // nested loops for correct table joining
        for (Seq<String> l : left) {
            boolean noMatch = true;
            for (Seq<String> r : right) {
                if(l.get(0).equals(r.get(0))) {
                    noMatch = false;
                    String rAdd = r.get(0);
                    r.remove(r.get(0));

                    Seq<String> newRow = new LinkedSeq<>();

                    // adding left values
                    for (String val : l) {
                        newRow.append(val);
                    }
                    //adding right values
                    for (String val : r) {
                        if (!newRow.contains(val)) {
                            newRow.append(val);
                        }
                    }

                    joined.append(newRow);
                    r.insertBefore(rAdd, r.get(0));


                }
            }

            // no match handling
            if (noMatch) {
                joined.append(l);
            }

            // extra column handling
            int joinedSize = joined.get(0).size();

            for (Seq<String> row : joined) {
                while (row.size() < joinedSize) {
                    row.append("");
                }
            }

        }

        return joined;

    }

    /**
     * This method converts a Seq<Seq<String>> into a String that is in
     * the form of a csv file.
     */
    public static String toCSV(Seq<Seq<String>> table) {
        StringBuilder csv = new StringBuilder();
        for (Seq<String> item : table) {
            boolean first = true;
            for (String elem : item) {
                csv.append(elem);
                csv.append(",");
            }
            csv.deleteCharAt(csv.length()-1);
            csv.append("\n");
        }
        return csv.toString();
    }

    /**
     * This main method takes in exactly 2 CSV files. After converting the two
     * files to lists, it joins the two tables. Then, it prints the joined table
     * into System.out The input tables MUST be rectangular.
     */

    public static void main(String[] args) throws IOException {

        if (args.length != 2){
            System.err.println(("User does not provide exactly 2 arguments"));
            System.exit(1);
        }
        try {
            // assigning files to variables
            String leftFile = args[0];
            String rightFile = args[1];

            // converting files to lists
            Seq<Seq<String>> leftTable = csvToList(leftFile);
            Seq<Seq<String>> rightTable = csvToList(rightFile);

            // handle non-rectangluar
            boolean isRectangular = true;
            int lcols = leftTable.get(0).size();
            int rcols = rightTable.get(0).size();

            for (Seq<String> row : leftTable) {
                if (row.size() != lcols) {
                    isRectangular = false;
                }
            }

            for (Seq<String> row : rightTable) {
                if (row.size() != rcols) {
                    isRectangular = false;
                }
            }

            if (!isRectangular) {
                System.err.println("Input tables are not rectangular.");
                System.exit(1);
            }


            // joining of tables and conversion to csv
            Seq<Seq<String>> joinedTable = join(leftTable, rightTable);
            String csv = toCSV(joinedTable);

            System.out.println(csv);
        }

        catch (FileNotFoundException e) {
            System.err.println("The input tables were unable to be read.");
            System.exit(1);
        } catch (IOException e) {
            System.err.println("The input tables were unable to be read");
            System.exit(1);
        } catch (Exception e) {
            System.err.println("There was an error while attempting to read the input tables");
            System.exit(1);
        }


    }

}
