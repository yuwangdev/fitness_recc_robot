package data;

import controller.ControllerImpl;
import modelling.*;
import org.assertj.core.util.Lists;
import org.junit.Test;
import org.springframework.http.ResponseEntity;
import service.RecResult;
import utils.Utils;

import java.sql.Timestamp;
import java.util.List;

public class FitnessModelRequestEntityTest {


    @Test
    public void mockRequestForModel() throws Exception {

        FitnessModelRequestEntity fm = new FitnessModelRequestEntity();
        fm.setLifeStyleForActConsumption("medium");
        fm.setMale(true);
        fm.setHeight(1.85);
        fm.setWeight(103);
        fm.setAge(25);
        fm.setNeck(43);
        fm.setAbdomen(116);
        fm.setDeltaWeightGoal(13);
        fm.setDurationOfGoal(365);
        fm.setTime("morning");
        fm.setFoodStyle("high protein");
        fm.setUserId("user1234");
        fm.setTimeStamp(new Timestamp(System.currentTimeMillis()).toString());


        List<FoodRequest> foodRequestLis = Lists.newArrayList();
        List<ExcersieRequestForCPA> excersieRequestForCPAS = Lists.newArrayList();

        foodRequestLis.add(new FoodRequest("1 egg", 2));
        foodRequestLis.add(new FoodRequest("250ml soy milk", 1));
        fm.setFoodRequestLis(foodRequestLis);


        excersieRequestForCPAS.add(new ExcersieRequestForCPA("pushup", 25.5));
        excersieRequestForCPAS.add(new ExcersieRequestForCPA("squad", 27.5));
        fm.setExcersieRequestForCPAS(excersieRequestForCPAS);

        System.out.println(Utils.getBeautifiedJson.apply(fm));

        ControllerImpl controller = new ControllerImpl();
        ResponseEntity<RecResult> recResultResponseEntity = controller.sendModel(fm);

        System.out.println(recResultResponseEntity.getBody().toString());


    }


    @Test
    public void mockResponseForModel() throws Exception {

        RecResult recResult = new RecResult();

        ReccFood reccFood = new ReccFood(3.3, 4.4, 5.5);

        ReccEx reccEx0 = new ReccEx(new Exercise("pushup", 0.15, true), 5.8);

        recResult.setAnaerobicRec(reccEx0);
        recResult.setAerobicRec(reccEx0);
        recResult.setFoodRec(reccFood);


    }

}