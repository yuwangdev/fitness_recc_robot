package modelling;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Set;

public class Modeler {

    private static final Logger logger = LoggerFactory.getLogger(Modeler.class);

    // init value
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


    // data from db
    private Set<String> previousOxygenList;
    private Set<String> previousWithoutList;


    public void setLifeStyleForActConsumption(String lifeStyleForActConsumption) {
        this.lifeStyleForActConsumption = lifeStyleForActConsumption;
    }

    public void setMale(boolean male) {
        isMale = male;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public void setAge(double age) {
        this.age = age;
    }

    public void setNeck(double neck) {
        this.neck = neck;
    }

    public void setAbdomen(double abdomen) {
        this.abdomen = abdomen;
    }

    public void setFoodRequestLis(List<FoodRequest> foodRequestLis) {
        this.foodRequestLis = foodRequestLis;
    }

    public void setExcersieRequestForCPAS(List<ExcersieRequestForCPA> excersieRequestForCPAS) {
        this.excersieRequestForCPAS = excersieRequestForCPAS;
    }

    public void setDeltaWeightGoal(double deltaWeightGoal) {
        this.deltaWeightGoal = deltaWeightGoal;
    }

    public void setDurationOfGoal(double durationOfGoal) {
        this.durationOfGoal = durationOfGoal;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public void setFoodStyle(String foodStyle) {
        this.foodStyle = foodStyle;
    }

    public void setPreviousOxygenList(Set<String> previousOxygenList) {
        this.previousOxygenList = previousOxygenList;
    }

    public void setPreviousWithoutList(Set<String> previousWithoutList) {
        this.previousWithoutList = previousWithoutList;
    }

    // intermediate variables
    private double foodIntakeCal;
    private double bmaCal;
    private double cpaCal;
    private double acCal;
    private double targetCal;
    private double foodPerTimeParameter;
    private double bfr;
    private double caloriesNeedToSpendByExercise;


    // rec result
    private ReccFood reccFood;
    private ReccEx[] reccExes;
    private double rewards;

    public double getRewards() {
        return this.rewards;
    }

    public ReccEx[] getReccExes() {
        return reccExes;
    }

    public Modeler() {

    }

    public void init() {
        initFoodIntakeValue(this.foodRequestLis);
        initBMAValue(this.isMale, this.height, this.weight, this.age, this.neck, this.abdomen);
        initCPAValue(this.excersieRequestForCPAS);
        initACValue(this.lifeStyleForActConsumption);
        initTargetValue(this.deltaWeightGoal, this.durationOfGoal);
        initFoodPerTimeParameter(this.time);
    }


    public void optimize() {
        optimizeFood();
        optimizeEx();

        this.rewards = (cpaCal + targetCal - foodIntakeCal) * 0.1;


    }

    private void optimizeEx() {
        logger.info("start to optimize exercise section");
        ExerciseOptimizer exerciseOptimizer = new ExerciseOptimizer(this.durationOfGoal, this.caloriesNeedToSpendByExercise, this.previousOxygenList, this.previousWithoutList, this.weight);
        exerciseOptimizer.optimize();
        this.reccExes = exerciseOptimizer.getRecs();
    }

    private void optimizeFood() {

        logger.info("start to optimize food section");

        AdjustmentFoodService adjustmentFoodService = AdjustmentFoodService.getInstance();
        FoodAdjustDelta foodAdjustDelta = adjustmentFoodService.calcuateFoodAjustDelta(
                this.foodPerTimeParameter,
                this.foodRequestLis,
                this.foodStyle,
                this.bmaCal,
                this.bfr
        );

        logger.info("get the foodAdjustDelta of " + foodAdjustDelta.toString());

        ReccFood reccFood = new ReccFood(foodAdjustDelta.getDeltaCarbon(),
                foodAdjustDelta.getDeltaFat(),
                foodAdjustDelta.getDeltaProtein()
        );

        this.reccFood = reccFood;

        logger.info("finish the recc on food = " + this.reccFood.toString());

        FoodAdjustDelta updatedFoodDividend = new FoodAdjustDelta();
        updatedFoodDividend.setDeltaCarbon(FoodIntake.getInstance().calcualteCurrentFoodComponentVolume(this.foodRequestLis).getDeltaCarbon() + foodAdjustDelta.getDeltaCarbon());
        updatedFoodDividend.setDeltaFat(FoodIntake.getInstance().calcualteCurrentFoodComponentVolume(this.foodRequestLis).getDeltaFat() + foodAdjustDelta.getDeltaFat());
        updatedFoodDividend.setDeltaProtein(FoodIntake.getInstance().calcualteCurrentFoodComponentVolume(this.foodRequestLis).getDeltaProtein() + foodAdjustDelta.getDeltaProtein());


        this.foodIntakeCal = AdjustmentFoodService.getInstance().calculateSumFoodIntakeCalFromDividend(updatedFoodDividend);

        logger.info("update foodIntakeCal of " + this.foodIntakeCal);

        this.caloriesNeedToSpendByExercise =
                Math.abs(this.targetCal - this.foodIntakeCal + this.bmaCal + this.acCal + this.acCal);

        logger.info("set caloriesNeedToSpendByExercise of " + this.caloriesNeedToSpendByExercise);


    }


    private void initFoodPerTimeParameter(String time) {
        logger.info("init FoodPerTimeParameter");
        double para = 0.0;
        switch (time) {
            case "morning":
                para = 0.3;
            case "afternoon":
                para = 0.3;
            case "evening":
                para = 0.3;
            default:
                para = 0.3;
        }

        this.foodPerTimeParameter = para;
        logger.info("set foodPerTimeParameter = " + this.foodPerTimeParameter);
    }


    private void initFoodIntakeValue(List<FoodRequest> foodRequestList) {
        logger.info("init foot intake");
        FoodIntake foodIntake = FoodIntake.getInstance();
        this.foodIntakeCal = foodIntake.getSumCalorieOfFoodIntakeList(foodRequestList);
        logger.info("set foodIntakeCal = " + this.foodIntakeCal);
    }

    private void initBMAValue(boolean isMale, double height, double weight, double age, double neck, double abdomen) {
        logger.info("init BMA value");
        BaseMetaAct baseMetaAct = new BaseMetaAct(isMale, height, weight, age, neck, abdomen);
        this.bmaCal = baseMetaAct.calculateBmaCal();
        this.bfr = baseMetaAct.calculateBodyFatRate();
        logger.info("set bmaCal = " + this.bmaCal);
        logger.info("set bfr = " + this.bfr);
    }


    private void initCPAValue(List<ExcersieRequestForCPA> excersieRequestForCPAS) {
        logger.info("init CPA value");
        CurrPhyAct currPhyAct = CurrPhyAct.getInstance();
        this.cpaCal = currPhyAct.getSumCalorieOfCurrPhyActList(excersieRequestForCPAS, this.weight);
        logger.info("set cpaCal = " + this.cpaCal);
    }


    private void initACValue(String lifeStyleForActConsumption) {
        logger.info("init AC value");
        ActConsumption actConsumption = new ActConsumption();
        this.acCal = actConsumption.calculateActConsumption(lifeStyleForActConsumption);
        logger.info("set acCal = " + this.acCal);
    }


    private void initTargetValue(double deltaWeightGoal, double durationOfGoal) {
        logger.info("init Target value");
        Target target = new Target(deltaWeightGoal, durationOfGoal);
        this.targetCal = target.calcualteTargetCal();
        logger.info("set targetCal = " + this.targetCal);
    }

    public ReccFood getReccFood() {
        return reccFood;
    }


}
