package modelling;

public class ReccEx {
    private Exercise exercise;
    private double duration;

    public ReccEx(Exercise exercise, double duration) {
        this.exercise = exercise;
        this.duration = duration;
    }

    public Exercise getExercise() {
        return exercise;
    }

    public void setExercise(Exercise exercise) {
        this.exercise = exercise;
    }

    public double getDuration() {
        return duration;
    }

    public void setDuration(double duration) {
        this.duration = duration;
    }

    @Override
    public String toString() {
        return "ReccEx{" +
                "exercise=" + exercise +
                ", duration=" + duration +
                '}';
    }
}
