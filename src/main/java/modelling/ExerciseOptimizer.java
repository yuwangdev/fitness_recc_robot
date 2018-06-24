package modelling;

import com.google.common.collect.Lists;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;

public class ExerciseOptimizer {

    private static final Logger logger = LoggerFactory.getLogger(ExerciseOptimizer.class);


    private List<Exercise> oxygenList;
    private List<Exercise> withoutList;

    private double durationTarget;
    private double calorieTarget;

    private Set<String> previousOxygenList;
    private Set<String> previousWithoutList;

    private double weight;


    private ReccEx[] recs;

    public ExerciseOptimizer(double durationTarget, double calorieTarget, Set<String> previousOxygenList, Set<String> previousWithoutList, double weight) {
        this.durationTarget = 30;
        this.calorieTarget = calorieTarget;
        this.previousOxygenList = previousOxygenList;
        this.previousWithoutList = previousWithoutList;
        initValues();
        this.recs = new ReccEx[2];
        this.weight = weight;
    }

    private void initValues() {

        this.oxygenList = Lists.newArrayList();
        this.withoutList = Lists.newArrayList();

        Map<String, Exercise> database = CurrPhyAct.getInstance().getExerciseDatabase();
        for (String key : database.keySet()) {
            if (database.get(key).isAerobic()) {
                this.oxygenList.add(database.get(key));
            } else {
                this.withoutList.add(database.get(key));
            }
        }


    }

    public ReccEx[] getRecs() {
        return this.recs;
    }


    public void optimize() {
        this.recs[0] = optimizeForOxygen();
        this.recs[1] = optimizeForWithout();
    }

    private ReccEx optimizeForWithout() {
        Exercise tempEx = new Exercise();

        if (this.previousWithoutList.isEmpty()) {
            int randomNum = new Random().nextInt((this.withoutList.size() - 1) + 1) + 0;
            tempEx.setAerobic(false);
            tempEx.setName(this.withoutList.get(randomNum).getName());
            tempEx.setCaloriePerMin((this.weight * 2.2 + 50) / 60);
            double shouldDuration = this.calorieTarget / tempEx.getCaloriePerMin();
            return new ReccEx(tempEx, shouldDuration > this.durationTarget ? this.durationTarget : shouldDuration);
        }


        return new ReccEx(tempEx, this.durationTarget);
    }

    private ReccEx optimizeForOxygen() {

        Exercise tempEx = new Exercise();
        double tempCalDiff = Double.MAX_VALUE;


        if (this.previousOxygenList.isEmpty()) {
            int randomNum = new Random().nextInt((this.oxygenList.size() - 1) + 1) + 0;
            tempEx.setAerobic(true);
            tempEx.setName(this.oxygenList.get(randomNum).getName());
            tempEx.setCaloriePerMin(this.oxygenList.get(randomNum).getCaloriePerMin());
            double shouldDuration = this.calorieTarget / (tempEx.getCaloriePerMin() * this.weight);
            return new ReccEx(tempEx, shouldDuration > this.durationTarget ? this.durationTarget : shouldDuration);
        }

        for (Exercise exercise : this.oxygenList) {
            if (!this.previousOxygenList.contains(exercise.getName())) {
                double currentSpend = exercise.getCaloriePerMin() * this.durationTarget * this.weight;
                if (Math.abs(currentSpend - this.calorieTarget) < tempCalDiff) {
                    tempCalDiff = Math.abs(currentSpend - this.calorieTarget);
                    tempEx.setAerobic(true);
                    tempEx.setName(exercise.getName());
                    tempEx.setCaloriePerMin(exercise.getCaloriePerMin());
                }

            }

        }

        return new ReccEx(tempEx, this.durationTarget);


    }


}
