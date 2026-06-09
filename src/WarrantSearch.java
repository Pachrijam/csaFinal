import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class WarrantSearch {

    public static ArrayList<Warrant> loadData(String fileName)
            throws FileNotFoundException {

        ArrayList<Warrant> warrants = new ArrayList<>();

        File file = new File(fileName);
        Scanner fileReader = new Scanner(file);

        // Skip header row
        fileReader.nextLine();

        while(fileReader.hasNextLine()) {

            String line = fileReader.nextLine();

            String[] data = line.split(",");

            String plate = data[0];
            String sex = data[1];
            int crimeCode = Integer.parseInt(data[2]);
            String crimeDescription = data[3];
            int premiseCode = Integer.parseInt(data[4]);
            boolean dangerous = Boolean.parseBoolean(data[5]);

            warrants.add(new Warrant(
                    plate,
                    sex,
                    crimeCode,
                    crimeDescription,
                    premiseCode,
                    dangerous));
        }

        fileReader.close();

        return warrants;
    }

    public static void main(String[] args)
            throws FileNotFoundException {

        ArrayList<Warrant> warrants =
                loadData("CrimeData2.csv");

        System.out.println(
                "Successfully loaded "
                + warrants.size()
                + " warrants.");
    }
}
