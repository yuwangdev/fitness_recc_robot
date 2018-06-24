package data;

import modelling.ExcersieRequestForCPA;
import modelling.FoodRequest;

import java.util.List;

public class FitnessModelRequestEntity {
    private String lifeStyleForActConsumption;
    private boolean isMale;
    private double height;
    private double weight;
    private double age;
    private double neck;
    private double abdomen;
    private List<FoodRequest> foodRequestLis;
    private List<ExcersieRequestForCPA> excersieRequestForCPAS;
    private double deltaWeightGoal;
    private double durationOfGoal;
    private String time;
    private String foodStyle;
    private String timeStamp;

    private String userId;

    public FitnessModelRequestEntity() {
    }

    public String getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(String timeStamp) {
        this.timeStamp = timeStamp;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }


    public String getLifeStyleForActConsumption() {
        return lifeStyleForActConsumption;
    }

    public void setLifeStyleForActConsumption(String lifeStyleForActConsumption) {
        this.lifeStyleForActConsumption = lifeStyleForActConsumption;
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

    public List<FoodRequest> getFoodRequestLis() {
        return foodRequestLis;
    }

    public void setFoodRequestLis(List<FoodRequest> foodRequestLis) {
        this.foodRequestLis = foodRequestLis;
    }

    public List<ExcersieRequestForCPA> getExcersieRequestForCPAS() {
        return excersieRequestForCPAS;
    }

    public void setExcersieRequestForCPAS(List<ExcersieRequestForCPA> excersieRequestForCPAS) {
        this.excersieRequestForCPAS = excersieRequestForCPAS;
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

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getFoodStyle() {
        return foodStyle;
    }

    public void setFoodStyle(String foodStyle) {
        this.foodStyle = foodStyle;
    }

    @Override
    public String toString() {
        return "FitnessModelRequestEntity{" +
                "lifeStyleForActConsumption='" + lifeStyleForActConsumption + '\'' +
                ", isMale=" + isMale +
                ", height=" + height +
                ", weight=" + weight +
                ", age=" + age +
                ", neck=" + neck +
                ", abdomen=" + abdomen +
                ", foodRequestLis=" + foodRequestLis +
                ", excersieRequestForCPAS=" + excersieRequestForCPAS +
                ", deltaWeightGoal=" + deltaWeightGoal +
                ", durationOfGoal=" + durationOfGoal +
                ", time='" + time + '\'' +
                ", foodStyle='" + foodStyle + '\'' +
                '}';
    }
}
