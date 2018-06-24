package service;

import org.junit.Test;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Set;

public class PreviousExerciseServiceTest {


    @Test
    public void test() {

        String ts = "2018-06-24 02:56:00.152";
        try {
            Timestamp currentTs = new Timestamp(new SimpleDateFormat("yyyy-MM-dd hh:mm:ss.SSS").parse(ts).getTime());
            System.out.println(currentTs);
        } catch (ParseException e) {
            e.printStackTrace();
        }


    }

    @Test
    public void calculatePrev() throws Exception {


        PreviousExerciseService previousExerciseService = new PreviousExerciseService();
       // Set<String> aero = previousExerciseService.calculatePrev("user123", "2018-06-24 02:56:00.152", true);
        Set<String> anero = previousExerciseService.calculatePrev("user123", "2018-06-24 02:56:00.152", false);
       // System.out.println(aero);
        System.out.println(anero);


    }

}


// "2018-06-24 02:56:00.152"