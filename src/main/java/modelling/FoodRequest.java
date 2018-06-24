package modelling;

public class FoodRequest {
    private String foodName;
    private double quant;

    public FoodRequest(String foodName, double quant) {
        this.foodName = foodName;
        this.quant = quant;
    }

    public FoodRequest() {
    }

    public String getFoodName() {
        return foodName;
    }

    public void setFoodName(String foodName) {
        this.foodName = foodName;
    }

    public double getQuant() {
        return quant;
    }

    public void setQuant(double quant) {
        this.quant = quant;
    }

    @Override
    public String toString() {
        return "FoodRequest{" +
                "foodName='" + foodName + '\'' +
                ", quant=" + quant +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof FoodRequest)) return false;

        FoodRequest that = (FoodRequest) o;

        if (Double.compare(that.quant, quant) != 0) return false;
        return foodName != null ? foodName.equals(that.foodName) : that.foodName == null;
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = foodName != null ? foodName.hashCode() : 0;
        temp = Double.doubleToLongBits(quant);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        return result;
    }
}
