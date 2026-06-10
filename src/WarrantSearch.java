import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class WarrantSearch {

    private static final String DEFAULT_DATA_FILE = "src/CrimeData.csv";

    private static String[] splitCSV(String line) {
        List<String> fields = new ArrayList<>();
        StringBuilder current = new StringBuilder();
        boolean inQuotes = false;

        for (int i = 0; i < line.length(); i++) {
            char c = line.charAt(i);
            if (c == '"') {
                inQuotes = !inQuotes;
            } else if (c == ',' && !inQuotes) {
                fields.add(current.toString());
                current.setLength(0);
            } else {
                current.append(c);
            }
        }

        fields.add(current.toString());
        return fields.toArray(new String[0]);
    }

    private static boolean inferDangerous(String crimeDescription) {
        if (crimeDescription == null) return false;

        String upper = crimeDescription.toUpperCase();
        return upper.contains("ASSAULT")
                || upper.contains("ROBBERY")
                || upper.contains("RAPE")
                || upper.contains("BATTERY")
                || upper.contains("THREAT")
                || upper.contains("BURGLARY")
                || upper.contains("HOMICIDE")
                || upper.contains("AGGRAVATED");
    }

    public static ArrayList<Warrant> loadData(String fileName) throws FileNotFoundException {
        ArrayList<Warrant> warrants = new ArrayList<>();
        File file = new File(fileName);

        try (Scanner fileReader = new Scanner(file)) {
            if (fileReader.hasNextLine()) {
                fileReader.nextLine();
            }

            while (fileReader.hasNextLine()) {
                String line = fileReader.nextLine().trim();
                if (line.isEmpty()) {
                    continue;
                }

                String[] data = splitCSV(line);
                if (data.length < 7) {
                    continue;
                }

                try {
                    int crimeCode = data[0].trim().isEmpty() ? 0 : Integer.parseInt(data[0].trim());
                    String crimeDescription = data[1].trim();
                    String sex = data[4].trim();
                    String plate = data[5].trim();
                    int premiseCode = data[6].trim().isEmpty() ? 0 : Integer.parseInt(data[6].trim());

                    warrants.add(new Warrant(
                            plate,
                            sex,
                            crimeCode,
                            crimeDescription,
                            premiseCode,
                            inferDangerous(crimeDescription)));
                } catch (NumberFormatException | ArrayIndexOutOfBoundsException ignored) {
                    // skip malformed rows
                }
            }
        }

        return warrants;
    }

    private static String normalizeSex(String sex) {
        if (sex == null) {
            return "UNKNOWN";
        }
        if (sex.trim().equalsIgnoreCase("M")) {
            return "MALE";
        }
        if (sex.trim().equalsIgnoreCase("F")) {
            return "FEMALE";
        }
        return "UNKNOWN";
    }

    private static List<Warrant> findByPlate(List<Warrant> warrants, String plate) {
        List<Warrant> matches = new ArrayList<>();
        for (Warrant warrant : warrants) {
            if (warrant.getLicensePlate().equalsIgnoreCase(plate)) {
                matches.add(warrant);
            }
        }
        return matches;
    }

    private static List<Warrant> findByPremiseCode(List<Warrant> warrants, int premiseCode) {
        List<Warrant> matches = new ArrayList<>();
        for (Warrant warrant : warrants) {
            if (warrant.getPremiseCode() == premiseCode) {
                matches.add(warrant);
            }
        }
        return matches;
    }

    private static void printWarrantResults(List<Warrant> matches) {
        if (matches.isEmpty()) {
            System.out.println("NO WARRANT");
            return;
        }

        System.out.println("DRIVER: " + normalizeSex(matches.get(0).getSex()));
        System.out.println("WITH FOLLOWING WARRANT:");
        for (Warrant match : matches) {
            System.out.println("CODE: " + match.getCrimeCode() + " (" + match.getCrimeDescription() + ")");
            if (match.isDangerous()) {
                System.out.println("DANGEROUS");
            }
        }
    }

    private static void searchByPlate(List<Warrant> warrants, Scanner scan) {
        System.out.print("License plate: ");
        String plate = scan.nextLine().trim();
        printWarrantResults(findByPlate(warrants, plate));
    }

    private static void searchByPremise(List<Warrant> warrants, Scanner scan) {
        System.out.print("Premise code: ");
        String premiseCodeStr = scan.nextLine().trim();

        int premiseCode;
        try {
            premiseCode = Integer.parseInt(premiseCodeStr);
        } catch (NumberFormatException e) {
            System.out.println("Invalid premise code. Please enter a numeric value.");
            return;
        }

        List<Warrant> premiseMatches = findByPremiseCode(warrants, premiseCode);
        if (premiseMatches.isEmpty()) {
            System.out.println("0 Local Warrants");
            return;
        }

        System.out.println(premiseMatches.size() + " Local Warrants:");
        for (int i = 0; i < premiseMatches.size(); i++) {
            System.out.println("Warrant " + (i + 1) + " - " + premiseMatches.get(i).getLicensePlate());
        }

        System.out.print("Please enter license plate of warrant for details: ");
        String selectedPlate = scan.nextLine().trim();
        printWarrantResults(findByPlate(premiseMatches, selectedPlate));
    }

    public static void main(String[] args) throws FileNotFoundException {
        Scanner scan = new Scanner(System.in);
        String fileToLoad = (args != null && args.length > 0) ? args[0] : DEFAULT_DATA_FILE;
        ArrayList<Warrant> warrants = loadData(fileToLoad);

        System.out.println("Search by license plate or premise code? (Enter 'plate' or 'premise')");
        String searchType = scan.nextLine().toLowerCase();

        if (searchType.equals("plate")) {
            searchByPlate(warrants, scan);
        } else if (searchType.equals("premise")) {
            searchByPremise(warrants, scan);
        } else {
            System.out.println("Invalid search type. Please enter 'plate' or 'premise'.");
        }
    }
}
