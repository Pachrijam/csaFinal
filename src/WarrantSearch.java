import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class WarrantSearch {

    /**
     * Simple CSV splitter that ignores commas inside quoted fields.
     * This is sufficient for basic CSV files used in class projects.
     */
    private static String[] splitCSV(String line) {
        // split on commas that are not inside quotes
        return line.split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)", -1);
    }

    private static boolean inferDangerous(String crimeDescription) {
        if (crimeDescription == null) return false;
        String d = crimeDescription.toUpperCase();
        return d.contains("ASSAULT")
                || d.contains("ROBBERY")
                || d.contains("RAPE")
                || d.contains("BATTERY")
                || d.contains("THREAT")
                || d.contains("BURGLARY")
                || d.contains("HOMICIDE")
                || d.contains("AGGRAVATED");
    }

    public static ArrayList<Warrant> loadData(String fileName)
            throws FileNotFoundException {

        ArrayList<Warrant> warrants = new ArrayList<>();

        File file = new File(fileName);
        Scanner fileReader = new Scanner(file);

        // Skip header row if present
        if (fileReader.hasNextLine()) {
            fileReader.nextLine();
        }

        while (fileReader.hasNextLine()) {

            String line = fileReader.nextLine();
            if (line.trim().isEmpty()) continue;

            String[] data = splitCSV(line);

            // Defensive checks: we expect at least 7 columns for the fields we need.
            if (data.length < 7) {
                // skip malformed line
                continue;
            }

            try {
                // Based on CrimeData.csv header layout:
                // 0: Crime Code
                // 1: Crime Code Description
                // 2: MO Codes (ignored)
                // 3: Age (ignored)
                // 4: Sex
                // 5: Liscense Plate  (note header in CSV)
                // 6: Premise Code
                String crimeCodeStr = data[0].trim();
                int crimeCode = crimeCodeStr.isEmpty() ? 0 : Integer.parseInt(crimeCodeStr);

                String crimeDescription = data[1].trim();
                String sex = data[4].trim();
                String plate = data[5].trim();
                String premiseCodeStr = data[6].trim();
                int premiseCode = premiseCodeStr.isEmpty() ? 0 : Integer.parseInt(premiseCodeStr);

                boolean dangerous = inferDangerous(crimeDescription);

                warrants.add(new Warrant(
                        plate,
                        sex,
                        crimeCode,
                        crimeDescription,
                        premiseCode,
                        dangerous));
            } catch (NumberFormatException nfe) {
                // skip rows where numeric parsing fails
                continue;
            } catch (ArrayIndexOutOfBoundsException aioobe) {
                // defensive: skip malformed lines
                continue;
            }
        }

        fileReader.close();

        return warrants;
    }

    public static void main(String[] args)
            throws FileNotFoundException {

        String fileToLoad = (args != null && args.length > 0) ? args[0] : "src/CrimeData.csv";

        ArrayList<Warrant> warrants = loadData(fileToLoad);

        System.out.println("Successfully loaded " + warrants.size() + " warrants.");
    }
}
