package modelling;

public class ReccFood {

    private double carbonGram;
    private double fatGram;
    private double proteinGram;


    public ReccFood(double carbonCal, double fatCal, double proteinCal) {
        this.carbonGram = carbonCal / 4;
        this.fatGram = fatCal / 9;
        this.proteinGram = proteinCal / 4;
    }


    public double getCarbonGram() {
        return carbonGram;
    }


    public double getFatGram() {
        return fatGram;
    }


    public double getProteinGram() {
        return proteinGram;
    }


    @Override

    public String toString() {
        return "ReccFood{" +
                "carbonGram=" + carbonGram +
                ", fatGram=" + fatGram +
                ", proteinGram=" + proteinGram +
                '}';
    }
}
