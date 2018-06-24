package modelling;

public class Food {

    private String name;
    private double fatContent;
    private double carbonContent;
    private double proteinContent;


    private final double CaloriePerFat = 9;
    private final double CaloriePerCarbon = 4;
    private final double CaloriePerProtein = 4;


    public Food() {
    }

    public Food(String name, double fatContent, double carbonContent, double proteinContent) {
        this.name = name;
        this.fatContent = fatContent;
        this.carbonContent = carbonContent;
        this.proteinContent = proteinContent;
    }

    public double getKCalorieOfThisFood() {

        return CaloriePerCarbon * this.carbonContent
                + CaloriePerFat * this.fatContent
                + CaloriePerProtein * this.proteinContent;

    }


    public double getCaloriePerFat() {
        return CaloriePerFat;
    }

    public double getCaloriePerCarbon() {
        return CaloriePerCarbon;
    }

    public double getCaloriePerProtein() {
        return CaloriePerProtein;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getFatContent() {
        return fatContent;
    }

    public void setFatContent(double fatContent) {
        this.fatContent = fatContent;
    }

    public double getCarbonContent() {
        return carbonContent;
    }

    public void setCarbonContent(double carbonContent) {
        this.carbonContent = carbonContent;
    }

    public double getProteinContent() {
        return proteinContent;
    }

    public void setProteinContent(double proteinContent) {
        this.proteinContent = proteinContent;
    }

    @Override
    public String toString() {
        return "Food{" +
                "name='" + name + '\'' +
                ", fatContent=" + fatContent +
                ", carbonContent=" + carbonContent +
                ", proteinContent=" + proteinContent +
                '}';
    }
}
