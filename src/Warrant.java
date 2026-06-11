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
    
    boolean isDangerous() {
        return dangerous;
    }
}
