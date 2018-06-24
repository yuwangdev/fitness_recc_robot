package service;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import dao.BlockChainDAO;
import data.BlockChainRequestEntity;
import data.FitnessModelRequestEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import utils.Utils;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class BlockChainService {

    private static final Logger logger = LoggerFactory.getLogger(BlockChainService.class);
    private static BlockChainService single_instance = null;
    private BlockChainDAO blockChainDAO;


    // private constructor restricted to this class itself
    private BlockChainService() {
        this.blockChainDAO = BlockChainDAO.getInstance();

    }

    // static method to create instance of Singleton class
    public static BlockChainService getInstance() {
        if (single_instance == null)
            single_instance = new BlockChainService();

        return single_instance;
    }

    public boolean saveMessages(FitnessModelRequestEntity fitnessModelRequestEntity) {
        this.blockChainDAO.save(Utils.getBeautifiedJson.apply(fitnessModelRequestEntity));
        return true;
    }


    public List<FitnessModelRequestEntity> obtainFmreList() {

        String str = this.blockChainDAO.read();

        List<BlockChainRequestEntity> blockChainRequestEntityArrayList = (ArrayList<BlockChainRequestEntity>) new Gson().fromJson(str,
                new TypeToken<ArrayList<BlockChainRequestEntity>>() {
                }.getType());

        List<FitnessModelRequestEntity> fitnessModelRequestEntityList =
                blockChainRequestEntityArrayList.parallelStream()
                        .filter(x -> x.getContent().length() > 30)
                        .map(x -> new Gson().fromJson(x.getContent(), FitnessModelRequestEntity.class)).collect(Collectors.toList());

        return fitnessModelRequestEntityList;

    }

    public List<FitnessModelRequestEntity> obtainFmreListPerUserId(String userId) {
        return obtainFmreList().parallelStream().filter(x -> x.getUserId().equalsIgnoreCase(userId)).collect(Collectors.toList());

    }
}
