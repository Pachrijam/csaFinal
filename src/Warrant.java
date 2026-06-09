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

    public int getPremiseCode() {
        return premiseCode;
    }

    public void printDetails() {

        System.out.println("License Plate: " + licensePlate);

        if(sex.equalsIgnoreCase("M"))
            System.out.println("DRIVER: MALE");
        else
            System.out.println("DRIVER: FEMALE");

        System.out.println("WITH FOLLOWING WARRANT:");
        System.out.println("CODE: " + crimeCode
                + " (" + crimeDescription + ")");

        if(dangerous)
            System.out.println("DANGEROUS");
    }
}
