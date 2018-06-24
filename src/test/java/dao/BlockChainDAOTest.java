package dao;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import data.BlockChainRequestEntity;
import data.FitnessModelRequestEntity;
import org.junit.Test;
import service.BlockChainService;

import java.util.ArrayList;
import java.util.List;

public class BlockChainDAOTest {


    @Test
    public void read() throws Exception {
        System.out.println(BlockChainDAO.getInstance().read());

        String str = BlockChainDAO.getInstance().read();


        ArrayList<BlockChainRequestEntity> list = (ArrayList<BlockChainRequestEntity>) new Gson().fromJson(str,
                new TypeToken<ArrayList<BlockChainRequestEntity>>() {
                }.getType());


        for (int i = 0; i < list.size(); i++) {
            BlockChainRequestEntity blockChainRequestEntity = list.get(i);
            FitnessModelRequestEntity fitnessModelRequestEntity = new Gson().fromJson(blockChainRequestEntity.getContent(), FitnessModelRequestEntity.class);
            System.out.println("");

        }


    }

    @Test
    public void save1() throws Exception {
        List<FitnessModelRequestEntity> fitnessModelRequestEntityList = BlockChainService.getInstance().obtainFmreList();
        System.out.println("dine");
    }

}


