package modelling;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class AdjustmentFoodService {

    private static final Logger logger = LoggerFactory.getLogger(AdjustmentFoodService.class);
    private static AdjustmentFoodService single_instance = null;


    private AdjustmentFoodService() {
        super();
    }

    public static AdjustmentFoodService getInstance() {
        if (single_instance == null)
            single_instance = new AdjustmentFoodService();

        return single_instance;
    }

    public FoodAdjustDelta calcuateFoodAjustDelta(double timeParameter, List<FoodRequest> foodRequestLis, String foodStyle, double bma, double bfr) {


        FoodAdjustDelta foodAdjustDelta = new FoodAdjustDelta();

        FoodAdjustDelta current = FoodIntake.getInstance().calcualteCurrentFoodComponentVolume(foodRequestLis);

        double carbonP = 0.5;
        double fatP = 0.25;
        double protP = 0.25;

        if (foodStyle.equalsIgnoreCase("high protein")) {
            carbonP = 0.4;
            fatP = 0.2;
            protP = 0.4;
        }


        double targetCalorie = bma * (bfr / 100 * 2.4 + 1.09);

        double targetCabon = targetCalorie * timeParameter * carbonP;
        double targetFat = targetCalorie * timeParameter * fatP;
        double targetProt = targetCalorie * timeParameter * protP;


        foodAdjustDelta.setDeltaProtein(targetProt > current.getDeltaProtein() ? targetProt - current.getDeltaProtein() : 0.0);
        foodAdjustDelta.setDeltaFat(targetFat > current.getDeltaFat() ? targetFat - current.getDeltaFat() : 0.0);
        foodAdjustDelta.setDeltaCarbon(targetCabon > current.getDeltaCarbon() ? targetCabon - current.getDeltaCarbon() : 0.0);


        return foodAdjustDelta;

    }

    public double calculateSumFoodIntakeCalFromDividend(FoodAdjustDelta updatedFoodDividend) {

        return updatedFoodDividend.getDeltaProtein() + updatedFoodDividend.getDeltaFat() + updatedFoodDividend.getDeltaCarbon();


    }

}
