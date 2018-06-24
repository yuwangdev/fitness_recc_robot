package modelling;

public class Target {

    private final double ALPHA = 7700;

    private double deltaWeightGoal;
    private double durationOfGoal;

    public Target(double deltaWeightGoal, double durationOfGoal) {
        this.deltaWeightGoal = deltaWeightGoal;
        this.durationOfGoal = durationOfGoal;
    }

    public Target() {
        super();
    }


    public double getDeltaWeightGoal() {
        return deltaWeightGoal;
    }

    public void setDeltaWeightGoal(double deltaWeightGoal) {
        this.deltaWeightGoal = deltaWeightGoal;
    }

    public double getDurationOfGoal() {
        return durationOfGoal;
    }

    public void setDurationOfGoal(double durationOfGoal) {
        this.durationOfGoal = durationOfGoal;
    }

    public double calcualteTargetCal() {
        return ALPHA * this.deltaWeightGoal / this.durationOfGoal;
    }

    @Override
    public String toString() {
        return "Target{" +
                "deltaWeightGoal=" + deltaWeightGoal +
                ", durationOfGoal=" + durationOfGoal +
                '}';
    }
}
