package com.hackathon.expensemanger.controller;

import com.fasterxml.jackson.annotation.ObjectIdGenerator;
import com.hackathon.expensemanger.dao.GoalDao;
import com.hackathon.expensemanger.entity.Goal;
import com.hackathon.expensemanger.util.HttpResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static com.hackathon.expensemanger.util.Constants.*;
import static com.hackathon.expensemanger.util.Constants.MSG_FOR_FAILED_INSERTION;

@RestController
@RequestMapping("/expensemanagement")
public class GoalsController {
    private static final Logger logger = LoggerFactory.getLogger(GoalsController.class);

    @Autowired
    private GoalDao goalDao;

    @GetMapping("/fetchGoals")
    public List<Goal> fetchGoals(@RequestBody Goal goal){
        HttpResponse<Goal> httpResponse = new HttpResponse<>();
        logger.info("Updating Goals object");
        List<Goal> goals = null;
        try {
            goals = goalDao.findById(goal.getGoalAccountId()).stream().toList();
            logger.info("End of method fetching goals");
        } catch (Exception e) {
            logger.error("exception occurred while updating goals : ", e);
        }
        return goals;
    }
    @PostMapping("/insertGoal")
    public HttpResponse<Goal> insertGoal(@RequestBody Goal goal) {
        HttpResponse<Goal> httpResponse = new HttpResponse<>();
        logger.info("Adding Goals object");
        try {
            Integer goalAchievedAmountInPercent = (goal.getGoalAchievedAmount().divide(goal.getGoalAmount())).multiply(new BigDecimal(100)).intValue();
            goal.setGoalPercentAchieved(goalAchievedAmountInPercent);
            goalDao.save(goal);
            httpResponse.setStatus(SUCCESS);
            httpResponse.setMessage(MSG_FOR_SUCCESSFUL_INSERTION);
            httpResponse.setObject(goal);
            logger.info("End of method adding goals");
        } catch (Exception e) {
            logger.error("exception occurred while inserting goals : ", e);
            httpResponse.setStatus(FAILURE);
            httpResponse.setMessage(MSG_FOR_FAILED_INSERTION);
        }
        return httpResponse;
    }

    @PostMapping("/updateGoal")
    public Goal updateGoal(@RequestBody Goal goal) {
        HttpResponse<Goal> httpResponse = new HttpResponse<>();
        logger.info("Updating Goals object");
        Goal goalObj = new Goal();
        try {
            goalObj = goalDao.findById(goal.getGoalId()).get();
            goalObj.setGoalId(goal.getGoalId());
            goalObj.setGoalAccountId(goal.getGoalAccountId());
            goalObj.setGoalAmount(goal.getGoalAmount());
            goalObj.setGoalType(goal.getGoalType());
            Integer goalAchievedAmountInPercent = (goal.getGoalAchievedAmount().divide(goal.getGoalAmount())).multiply(new BigDecimal(100)).intValue();
            goal.setGoalPercentAchieved(goalAchievedAmountInPercent);
            goalObj.setGoalAchievedAmount(goal.getGoalAchievedAmount());
            goalDao.save(goalObj);
            httpResponse.setStatus(SUCCESS);
            httpResponse.setMessage(MSG_FOR_SUCCESSFUL_UPDATE);
            httpResponse.setObject(goal);
            logger.info("End of method updating goals");
        } catch (Exception e) {
            logger.error("exception occurred while updating goals : ", e);
            httpResponse.setStatus(FAILURE);
            httpResponse.setMessage(MSG_FOR_FAILED_UPDATE);
        }
        return goalObj;
    }

    @PostMapping("/removeGoal")
    public HttpResponse<Goal> removeGoal(@RequestBody Goal goal) {
        HttpResponse<Goal> httpResponse = new HttpResponse<>();
        logger.info("Removing Goals object");
        Goal goalObj = new Goal();
        try {
            goalObj = goalDao.findById(goal.getGoalId()).get();
            goalDao.delete(goalObj);
            httpResponse.setStatus(SUCCESS);
            httpResponse.setMessage("Goal removed succefully.");
            httpResponse.setObject(goal);
            logger.info("End of method removing goals");
        } catch (Exception e) {
            logger.error("exception occurred while removing goals : ", e);
            httpResponse.setStatus(FAILURE);
            httpResponse.setMessage("Goal removal process failed.");
        }
        return httpResponse;
    }

}
