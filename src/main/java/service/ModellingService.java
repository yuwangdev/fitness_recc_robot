package service;

import modelling.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class ModellingService {

    private static final Logger logger = LoggerFactory.getLogger(ModellingService.class);

    private Modeler modeler;
    private String userId;
    private String timestamp;
    private PreviousExerciseService previousExerciseService;

    public ModellingService(String userId, String timestamp) {
        this.userId = userId;
        this.timestamp = timestamp;
        this.modeler = new Modeler();
        this.previousExerciseService = new PreviousExerciseService();
    }


    public RecResult calculateModelingResult(
            String lifeStyleForActConsumption,
            boolean isMale,
            double height,
            double weight,
            double age,
            double neck,
            double abdomen,
            List<FoodRequest> foodRequestLis,
            List<ExcersieRequestForCPA> excersieRequestForCPAS,
            double deltaWeightGoal,
            double durationOfGoal,
            String time,
            String foodStyle
    ) {

        this.modeler.setLifeStyleForActConsumption(lifeStyleForActConsumption);
        this.modeler.setMale(isMale);
        this.modeler.setHeight(height);
        this.modeler.setWeight(weight);
        this.modeler.setAge(age);
        this.modeler.setNeck(neck);
        this.modeler.setAbdomen(abdomen);
        this.modeler.setFoodRequestLis(foodRequestLis);
        this.modeler.setExcersieRequestForCPAS(excersieRequestForCPAS);
        this.modeler.setDeltaWeightGoal(deltaWeightGoal);
        this.modeler.setDurationOfGoal(durationOfGoal);
        this.modeler.setTime(time);
        this.modeler.setFoodStyle(foodStyle);
        this.modeler.setPreviousOxygenList(this.previousExerciseService.calculatePrev(this.userId, this.timestamp, true));
        this.modeler.setPreviousWithoutList(this.previousExerciseService.calculatePrev(this.userId, this.timestamp, false));


        this.modeler.init();
        this.modeler.optimize();
        RecResult recResult = new RecResult();

        ReccFood recFood = this.modeler.getReccFood();
        ReccEx[] reccExs = this.modeler.getReccExes();

        recResult.setFoodRec(recFood);
        recResult.setAerobicRec(reccExs[0]);
        recResult.setAnaerobicRec(reccExs[1]);
        recResult.setRewards(Math.abs(this.modeler.getRewards()));

        return recResult;


    }


}
