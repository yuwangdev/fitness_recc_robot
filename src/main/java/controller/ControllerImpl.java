package controller;


import data.FitnessModelRequestEntity;
import data.MetricsModelRequestEntity;
import data.MetricsModelResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import modelling.BaseMetaAct;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import service.BlockChainService;
import service.ModellingService;
import service.RecResult;
import utils.Utils;

@RestController
@RequestMapping(value = "/fitness")
@Api(value = "fitness_service", description = "Fitness Restful controller service")
public class ControllerImpl {

    private static final Logger logger = LoggerFactory.getLogger(ControllerImpl.class);


    @RequestMapping(value = "/echo", method = RequestMethod.GET)
    @ApiOperation(value = "Simply testing the service", response = ResponseEntity.class)
    public ResponseEntity<String> test() {
        return new ResponseEntity<>("testing the restful controller of fitness", HttpStatus.OK);

    }


    @RequestMapping(value = "/sendModel", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
    @ApiOperation(value = "send for modelling", response = ResponseEntity.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully"),
            @ApiResponse(code = 500, message = "Exception is encountered")
    })

    public ResponseEntity<RecResult> sendModel(
            @RequestBody FitnessModelRequestEntity fitnessModelRequestEntity) {


        logger.info("received the fitness message of " + Utils.getBeautifiedJson.apply(fitnessModelRequestEntity));
        ModellingService modellingService = new ModellingService(fitnessModelRequestEntity.getUserId(), fitnessModelRequestEntity.getTimeStamp().toString());
        RecResult re = modellingService.calculateModelingResult(
                fitnessModelRequestEntity.getLifeStyleForActConsumption(),
                fitnessModelRequestEntity.isMale(),
                fitnessModelRequestEntity.getHeight(),
                fitnessModelRequestEntity.getWeight(),
                fitnessModelRequestEntity.getAge(),
                fitnessModelRequestEntity.getNeck(),
                fitnessModelRequestEntity.getAbdomen(),
                fitnessModelRequestEntity.getFoodRequestLis(),
                fitnessModelRequestEntity.getExcersieRequestForCPAS(),
                fitnessModelRequestEntity.getDeltaWeightGoal(),
                fitnessModelRequestEntity.getDurationOfGoal(),
                fitnessModelRequestEntity.getTime(),
                fitnessModelRequestEntity.getFoodStyle()
        );


        BlockChainService.getInstance().saveMessages(fitnessModelRequestEntity);

        return new ResponseEntity<RecResult>(re, HttpStatus.OK);
    }


    @RequestMapping(value = "/metrics", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
    @ApiOperation(value = "send for modelling", response = ResponseEntity.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully"),
            @ApiResponse(code = 500, message = "Exception is encountered")
    })

    public ResponseEntity<MetricsModelResponse> metrics(
            @RequestBody MetricsModelRequestEntity metricsModelRequestEntity) {


        logger.info("received the fitness message of " + Utils.getBeautifiedJson.apply(metricsModelRequestEntity));
        MetricsModelResponse metricsModelResponse = new MetricsModelResponse();

        BaseMetaAct baseMetaAct = new BaseMetaAct(
                metricsModelRequestEntity.isMale(),
                metricsModelRequestEntity.getHeight(),
                metricsModelRequestEntity.getWeight(),
                metricsModelRequestEntity.getAge(),
                metricsModelRequestEntity.getNeck(),
                metricsModelRequestEntity.getAbdomen()
        );

        metricsModelResponse.setBfr(baseMetaAct.calculateBodyFatRate());
        metricsModelResponse.setBmi(baseMetaAct.calculateBmi());

        return new ResponseEntity<MetricsModelResponse>(metricsModelResponse, HttpStatus.OK);
    }


}

