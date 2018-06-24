package modelling;

public class BaseMetaAct {

    private boolean isMale;
    private double height;
    private double weight;
    private double age;


    private double neck;
    private double abdomen;

    public BaseMetaAct(boolean isMale, double height, double weight, double age, double neck, double abdomen) {
        this.isMale = isMale;
        this.height = height;
        this.weight = weight;
        this.age = age;
        this.neck = neck;
        this.abdomen = abdomen;
    }

    public BaseMetaAct() {
        super();
    }


    public double calculateBodyFatRate() {

        if (isMale) {

            return 10.1 - 0.239 * this.height * 100 + 0.8 * this.abdomen - 0.5 * this.neck;
        } else {
            return 19.2 - 0.239 * this.height * 100 + 0.8 * this.abdomen - 0.5 * this.neck;
        }

    }


    public double calculateBmaCal() {

        if (isMale) {
            return 66 + 13.75 * this.weight + 5 * this.height * 100 - 6.8 * this.age;
        } else {
            return 655 + 9.6 * this.weight + 1.8 * this.height * 100 - 4.7 * this.age;
        }

    }

    public double calculateBmi() {

        return this.weight / Math.pow(this.height, 2);
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
        return "BaseMetaAct{" +
                "isMale=" + isMale +
                ", height=" + height +
                ", weight=" + weight +
                ", age=" + age +
                ", neck=" + neck +
                ", abdomen=" + abdomen +
                '}';
    }
}
