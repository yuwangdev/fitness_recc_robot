package data;

public class MetricsModelResponse {
    private double bmi;
    private double bfr;

    public MetricsModelResponse() {
    }

    public double getBmi() {
        return bmi;
    }

    public void setBmi(double bmi) {
        this.bmi = bmi;
    }

    public double getBfr() {
        return bfr;
    }

    public void setBfr(double bfr) {
        this.bfr = bfr;
    }

    @Override
    public String toString() {
        return "MetricsModelResponse{" +
                "bmi=" + bmi +
                ", bfr=" + bfr +
                '}';
    }
}
