import java.time.LocalDate;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

public class MapDemo {
    public static void main(String[] args) {
        Map<String, LocalDate> bdays = new TreeMap<>();

        bdays.put("Alan Turing", LocalDate.of(1912, 6, 23));
        bdays.put("John von Neumann", LocalDate.of(1903, 12, 28));


        for (String key : bdays.keySet()) {
            System.out.println(key + ": " + bdays.get(key));
        }

        // TODO: Write a loop to print the name and birthday of everyone in bdays

        // TODO: Check if you know Katherine Johnson's birthday and print a message

    }
}
