package modelling;

import org.assertj.core.util.Lists;
import org.assertj.core.util.Sets;
import org.junit.Test;

import java.util.List;
import java.util.Set;

public class ModelerTest {

    @Test
    public void optimize() throws Exception {

        Modeler modeler = new Modeler();

        modeler.setLifeStyleForActConsumption("medium");
        modeler.setMale(true);
        modeler.setHeight(180);
        modeler.setWeight(175);
        modeler.setAge(28);
        modeler.setNeck(35);
        modeler.setAbdomen(80);
        modeler.setDeltaWeightGoal(30);
        modeler.setDurationOfGoal(10);
        modeler.setTime("morning");
        modeler.setFoodStyle("high protein");


        List<FoodRequest> foodRequestLis = Lists.newArrayList();
        List<ExcersieRequestForCPA> excersieRequestForCPAS = Lists.newArrayList();

        foodRequestLis.add(new FoodRequest("100g jasmine rice", 1));
        foodRequestLis.add(new FoodRequest("250ml soy milk", 0.5));
        modeler.setFoodRequestLis(foodRequestLis);

        excersieRequestForCPAS.add(new ExcersieRequestForCPA("dance", 1.5));
        modeler.setExcersieRequestForCPAS(excersieRequestForCPAS);

        Set<String> prevOxy = Sets.newHashSet();
        prevOxy.add("hiit");
        prevOxy.add("rolling");
        Set<String> prevWithout = Sets.newHashSet();
        prevWithout.add("legs");
        modeler.setPreviousOxygenList(prevOxy);
        modeler.setPreviousWithoutList(prevWithout);

        modeler.init();
        modeler.optimize();
        ReccFood reccFood = modeler.getReccFood();
        ReccEx[] reccExes = modeler.getReccExes();
        System.out.println("done");
        System.out.println(reccFood);
        System.out.println(reccExes[0]);
        System.out.println(reccExes[1]);


    }

}