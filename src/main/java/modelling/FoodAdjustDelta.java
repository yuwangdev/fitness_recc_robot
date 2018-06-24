package modelling;

public class FoodAdjustDelta {

    private double deltaCarbon;
    private double deltaFat;
    private double deltaProtein;

    public FoodAdjustDelta() {
        super();
    }

    public double getDeltaCarbon() {
        return deltaCarbon;
    }

    public void setDeltaCarbon(double deltaCarbon) {
        this.deltaCarbon = deltaCarbon;
    }

    public double getDeltaFat() {
        return deltaFat;
    }

    public void setDeltaFat(double deltaFat) {
        this.deltaFat = deltaFat;
    }

    public double getDeltaProtein() {
        return deltaProtein;
    }

    public void setDeltaProtein(double deltaProtein) {
        this.deltaProtein = deltaProtein;
    }

    @Override
    public String toString() {
        return "FoodAdjustDelta{" +
                "deltaCarbon=" + deltaCarbon +
                ", deltaFat=" + deltaFat +
                ", deltaProtein=" + deltaProtein +
                '}';
    }
}
