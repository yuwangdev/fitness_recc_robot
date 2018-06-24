package modelling;

import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Map;
import java.util.Set;

public class CurrPhyAct {

    private Map<String, Exercise> exerciseDatabase;
    private static final Logger logger = LoggerFactory.getLogger(CurrPhyAct.class);
    private static CurrPhyAct single_instance = null;

    private Set<String> aerobics;
    private Set<String> anaerobic;


    private CurrPhyAct() {
        initializeExerciseDatabase();
    }

    public static CurrPhyAct getInstance() {
        if (single_instance == null)
            single_instance = new CurrPhyAct();

        return single_instance;
    }

    private void initializeExerciseDatabase() {

        this.aerobics = Sets.newHashSet();
        this.anaerobic = Sets.newHashSet();

        this.exerciseDatabase = Maps.newHashMap();

        this.exerciseDatabase.put("run", new Exercise("run", 0.14, true));
        this.exerciseDatabase.put("rolling", new Exercise("rolling", 0.16, true));
        this.exerciseDatabase.put("swimming", new Exercise("swimming", 0.11, true));
        this.exerciseDatabase.put("elliptical machine", new Exercise("elliptical machine", 0.12, true));
        this.exerciseDatabase.put("hiit", new Exercise("hiit", 0.14, true));
        this.exerciseDatabase.put("boxing", new Exercise("boxing", 0.16, true));
        this.exerciseDatabase.put("walking", new Exercise("walking", 0.11, true));
        this.exerciseDatabase.put("dance", new Exercise("dance", 0.11, true));
        this.exerciseDatabase.put("step machine", new Exercise("step machine", 0.11, true));

        this.exerciseDatabase.put("chest", new Exercise("chest", 1, false));
        this.exerciseDatabase.put("back", new Exercise("back", 1, false));
        this.exerciseDatabase.put("shoulder", new Exercise("shoulder", 1, false));
        this.exerciseDatabase.put("arms", new Exercise("arms", 1, false));
        this.exerciseDatabase.put("legs", new Exercise("legs", 1, false));


    }

    public Set<String> getAerobics() {
        for (String key :
                this.exerciseDatabase.keySet()) {
            if (this.exerciseDatabase.get(key).isAerobic())
                this.aerobics.add(key);

        }


        return this.aerobics;
    }

    public Set<String> getAnaerobic() {
        for (String key :
                this.exerciseDatabase.keySet()) {
            if (!this.exerciseDatabase.get(key).isAerobic())
                this.anaerobic.add(key);

        }

        return this.anaerobic;
    }

    public Map<String, Exercise> getExerciseDatabase() {
        return exerciseDatabase;
    }

    public double queryOnThisExerciseForCal(String exerciseName, double weight) {
        if (!this.exerciseDatabase.containsKey(exerciseName)) {
            return 0.0;
        } else {

            if (this.exerciseDatabase.get(exerciseName).isAerobic()) {
                return this.exerciseDatabase.get(exerciseName).getCaloriePerMin() * weight;

            } else {
                return (weight * 2.2 + 50) / 60;
            }


        }
    }

    public double getSumCalorieOfCurrPhyActList(List<ExcersieRequestForCPA> excersieRequestForCPAS, double weight) {

        double result = 0.0;

        for (ExcersieRequestForCPA excersieRequestForCPA : excersieRequestForCPAS) {
            result += queryOnThisExerciseForCal(excersieRequestForCPA.getName(), weight) * excersieRequestForCPA.getDurationInMin();
        }
        return result;

    }

}
