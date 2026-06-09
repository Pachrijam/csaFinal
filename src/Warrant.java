public class Warrant {

    private String licensePlate;
    private String sex;
    private int crimeCode;
    private String crimeDescription;
    private int premiseCode;
    private boolean dangerous;

    public Warrant(String licensePlate,
                   String sex,
                   int crimeCode,
                   String crimeDescription,
                   int premiseCode,
                   boolean dangerous) {
        this.licensePlate = licensePlate;
        this.sex = sex;
        this.crimeCode = crimeCode;
        this.crimeDescription = crimeDescription;
        this.premiseCode = premiseCode;
        this.dangerous = dangerous;
    }

    public String getLicensePlate() {
        return licensePlate;
    }

    public String getSex() {
        return sex;
    }

    public int getCrimeCode() {
        return crimeCode;
    }

    public String getCrimeDescription() {
        return crimeDescription;
    }

    public int getPremiseCode() {
        return premiseCode;
    }

    public boolean isDangerous() {
        return dangerous;
    }

    public String getSexLabel() {
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

    public void printDetails() {
        System.out.println("License Plate: " + (licensePlate == null ? "UNKNOWN" : licensePlate));
        System.out.println("DRIVER: " + getSexLabel());
        System.out.println("WITH FOLLOWING WARRANT:");
        System.out.println("CODE: " + crimeCode + " (" + (crimeDescription == null ? "" : crimeDescription) + ")");
        if (dangerous) {
            System.out.println("DANGEROUS");
        }
    }

    @Override
    public String toString() {
        return "Warrant{" +
                "licensePlate='" + licensePlate + '\'' +
                ", sex='" + sex + '\'' +
                ", crimeCode=" + crimeCode +
                ", crimeDescription='" + crimeDescription + '\'' +
                ", premiseCode=" + premiseCode +
                ", dangerous=" + dangerous +
                '}';
    }
}
