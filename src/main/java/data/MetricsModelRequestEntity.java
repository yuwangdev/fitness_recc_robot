package data;

public class MetricsModelRequestEntity {
    private boolean isMale;
    private double height;
    private double weight;
    private double age;
    private double neck;
    private double abdomen;

    public MetricsModelRequestEntity() {
    }

    public boolean isMale() {
        return isMale;
    }

    public void setMale(boolean male) {
        isMale = male;
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public double getAge() {
        return age;
    }

    public void setAge(double age) {
        this.age = age;
    }

    public double getNeck() {
        return neck;
    }

    public void setNeck(double neck) {
        this.neck = neck;
    }

    public double getAbdomen() {
        return abdomen;
    }

    public void setAbdomen(double abdomen) {
        this.abdomen = abdomen;
    }

    @Override
    public String toString() {
        return "MetricsModelRequestEntity{" +
                "isMale=" + isMale +
                ", height=" + height +
                ", weight=" + weight +
                ", age=" + age +
                ", neck=" + neck +
                ", abdomen=" + abdomen +
                '}';
    }
}
