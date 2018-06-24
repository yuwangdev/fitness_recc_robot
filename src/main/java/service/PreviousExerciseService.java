package service;

import com.google.common.collect.Sets;
import data.FitnessModelRequestEntity;
import modelling.CurrPhyAct;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Set;

public class PreviousExerciseService {

    private static final Logger logger = LoggerFactory.getLogger(PreviousExerciseService.class);

    private final double OXYGEN_INTERVAL = 24;
    private final double WITHOUT_INTERVAL = 48;

    private BlockChainService blockChainService;

    public PreviousExerciseService() {
        this.blockChainService = BlockChainService.getInstance();
    }

    public Set<String> calculatePrev(String userId, String timestamp, boolean isAero) {

        double thres = isAero ? OXYGEN_INTERVAL : WITHOUT_INTERVAL;

        Set<String> categories = isAero ? CurrPhyAct.getInstance().getAerobics() :
                CurrPhyAct.getInstance().getAnaerobic();

        Set<String> set = Sets.newHashSet();
        List<FitnessModelRequestEntity> fitnessModelRequestEntityList = BlockChainService.getInstance().obtainFmreListPerUserId(userId);

        try {

            Timestamp currentTs = new java.sql.Timestamp(new SimpleDateFormat("yyyy-MM-dd hh:mm:ss.SSS").parse(timestamp).getTime());

            fitnessModelRequestEntityList.forEach(x -> {

                try {
                    Timestamp ts = new Timestamp(new SimpleDateFormat("yyyy-MM-dd hh:mm:ss.SSS").parse(x.getTimeStamp()).getTime());
                    long diffHours = (currentTs.getTime() - ts.getTime()) / (60 * 60 * 1000);
                    if (diffHours >= thres) {
                        x.getExcersieRequestForCPAS().forEach(y -> {
                            if (categories.contains(y.getName())) {

                                set.add(y.getName());
                            }
                        });
                    }

                } catch (ParseException e) {
                    e.printStackTrace();
                }
            });

        } catch (Exception e) { //this generic but you can control another types of exception
            // look the origin of excption
        }

        return set;

    }

}
