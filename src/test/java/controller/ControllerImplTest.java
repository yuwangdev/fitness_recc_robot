package controller;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.http.ResponseEntity;

public class ControllerImplTest {

    private String header = "application/json; charset=utf-8";

    @Test
    public void test1() throws Exception {

        ControllerImpl chatController = new ControllerImpl();

        ResponseEntity<String> responseEntity = chatController.test();
        Assert.assertTrue(responseEntity.getBody().equalsIgnoreCase("testing the restful controller of fitness"));


    }


}