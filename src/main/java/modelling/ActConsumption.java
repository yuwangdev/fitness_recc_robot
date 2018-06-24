package modelling;

public class ActConsumption {

    private final double LOWER = 200;
    private final double MEDIUM = 400;
    private final double HIGH = 600;

    public ActConsumption() {
    }

    public double calculateActConsumption(String lifeStyle) {

        switch (lifeStyle) {
            case "lower":
                return LOWER;
            case "medium":
                return MEDIUM;
            case "high":
                return HIGH;
            default:
                return MEDIUM;
        }

    }

}
