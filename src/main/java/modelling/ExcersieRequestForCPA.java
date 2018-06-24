package modelling;

public class ExcersieRequestForCPA {

    private String name;
    private double durationInMin;


    public ExcersieRequestForCPA() {
    }

    public ExcersieRequestForCPA(String name, double durationInMin) {
        this.name = name;
        this.durationInMin = durationInMin;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getDurationInMin() {
        return durationInMin;
    }

    public void setDurationInMin(double durationInMin) {
        this.durationInMin = durationInMin;
    }

    @Override
    public String toString() {
        return "ExcersieRequestForCPA{" +
                "name='" + name + '\'' +
                ", durationInMin=" + durationInMin +
                '}';
    }
}
