package modelling;

import com.google.common.collect.Maps;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Map;

public class FoodIntake {

    private static final Logger logger = LoggerFactory.getLogger(FoodIntake.class);
    private static FoodIntake single_instance = null;


    private Map<String, Food> foodDatabase;


    private FoodIntake() {
        initializeFoodDatabase();
    }

    public static FoodIntake getInstance() {
        if (single_instance == null)
            single_instance = new FoodIntake();

        return single_instance;
    }

    private void initializeFoodDatabase() {

        this.foodDatabase = Maps.newHashMap();

        this.foodDatabase.put("1 avocado", new Food("Avocado", 38, 12, 4));
        this.foodDatabase.put("1 egg", new Food("1 egg", 2.3, 0.3, 7));
        this.foodDatabase.put("100g jasmine rice", new Food("100g jasmine rice", 1, 22, 2));
        this.foodDatabase.put("1 carrot", new Food("1 carrot", 0, 12, 2));
        this.foodDatabase.put("100g noodles", new Food("100g noodles", 1, 41, 7));
        this.foodDatabase.put("100g tofu", new Food("100g tofu", 5, 2, 8));
        this.foodDatabase.put("10g chocolate", new Food("10g chocolate", 3, 5, 1));
        this.foodDatabase.put("1/4 cup nuts", new Food("1/4 cup nuts", 14, 7, 5));
        this.foodDatabase.put("3oz beef", new Food("3oz beef", 9, 1, 19));
        this.foodDatabase.put("chicken thigh", new Food("chicken thigh", 9, 0, 33));
        this.foodDatabase.put("1 cup mixed green", new Food("1 cup mixed green", 0, 0, 1));
        this.foodDatabase.put("250ml soy milk", new Food("250ml soy milk", 3, 3, 5));


    }

    public double queryOnThisFoodForCal(String foodName) {
        if (!this.foodDatabase.containsKey(foodName)) {
            return 0.0;
        } else {
            return this.foodDatabase.get(foodName).getKCalorieOfThisFood();
        }
    }

    public Food queryOnThisFoodForFood(String foodName) {
        if (!this.foodDatabase.containsKey(foodName)) {
            return null;
        } else {
            return this.foodDatabase.get(foodName);
        }
    }

    public double getSumCalorieOfFoodIntakeList(List<FoodRequest> intakeFoodList) {

        double result = 0.0;

        for (FoodRequest foodRequest : intakeFoodList) {
            result += queryOnThisFoodForCal(foodRequest.getFoodName()) * foodRequest.getQuant();
        }
        return result;

    }

    public FoodAdjustDelta calcualteCurrentFoodComponentVolume(List<FoodRequest> intakeFoodList) {


        FoodAdjustDelta foodAdjustDelta = new FoodAdjustDelta();


        double cab = 0.0;
        double fat = 0.0;
        double prot = 0.0;

        for (FoodRequest foodRequest :
                intakeFoodList) {

            double quant = foodRequest.getQuant();
            Food food = this.queryOnThisFoodForFood(foodRequest.getFoodName());
            if (food != null) {

                cab += food.getCarbonContent() * food.getCaloriePerCarbon() * quant;
                fat += food.getFatContent() * food.getCaloriePerFat() * quant;
                prot += food.getProteinContent() * food.getCaloriePerProtein() * quant;

            }

        }
        foodAdjustDelta.setDeltaCarbon(cab);
        foodAdjustDelta.setDeltaFat(fat);
        foodAdjustDelta.setDeltaProtein(prot);


        return foodAdjustDelta;


    }


}




