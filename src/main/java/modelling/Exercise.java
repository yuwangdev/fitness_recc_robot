package modelling;

public class Exercise {

    private String name;
    private double caloriePerMin;
    private boolean isAerobic;

    public Exercise(String name, double caloriePerMin, boolean isAerobic) {
        this.name = name;
        this.caloriePerMin = caloriePerMin;
        this.isAerobic = isAerobic;
    }

    public Exercise() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getCaloriePerMin() {
        return caloriePerMin;
    }

    public void setCaloriePerMin(double caloriePerMin) {
        this.caloriePerMin = caloriePerMin;
    }

    public boolean isAerobic() {
        return isAerobic;
    }

    public void setAerobic(boolean aerobic) {
        isAerobic = aerobic;
    }

    @Override
    public String toString() {
        return "Exercise{" +
                "name='" + name + '\'' +
                ", caloriePerMin=" + caloriePerMin +
                ", isAerobic=" + isAerobic +
                '}';
    }
}

