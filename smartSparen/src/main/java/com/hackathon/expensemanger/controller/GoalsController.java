package com.hackathon.expensemanger.controller;

import com.hackathon.expensemanger.dao.GoalDao;
import com.hackathon.expensemanger.entity.Goal;
import com.hackathon.expensemanger.util.HttpResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

import static com.hackathon.expensemanger.util.Constants.*;
import static com.hackathon.expensemanger.util.Constants.MSG_FOR_FAILED_INSERTION;

@RestController
@RequestMapping("/micro-saving-goal")
public class GoalsController {
    private static final Logger logger = LoggerFactory.getLogger(GoalsController.class);

    @Autowired
    private GoalDao goalDao;

    @PostMapping("/fetchGoals")
    public List<Goal> fetchGoal(@RequestBody Goal goal){
        logger.info("Updating Goals object");
        Optional<Goal> goals = null;
        try {
            goals = goalDao.findById(goal.getUserId());
            logger.info("End of method fetching goals");
        } catch (Exception e) {
            logger.error("exception occurred while updating goals : ", e);
        }
        return goals != null ? goals.stream().toList() : null;
    }
    @PostMapping("/insertGoal")
    public HttpResponse<Goal> insertGoal(@RequestBody Goal goal) {
        HttpResponse<Goal> httpResponse = new HttpResponse<>();
        logger.info("Adding Goals object");
        try {
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
    public HttpResponse<Goal> updateGoal(@RequestBody com.hackathon.expensemanger.bean.Goal goal) {
        HttpResponse<Goal> httpResponse = new HttpResponse<>();
        logger.info("Updating Goals object");
        com.hackathon.expensemanger.entity.Goal goalObj = new com.hackathon.expensemanger.entity.Goal();
        try {
            goalObj = goalDao.findById(goal.getGoalId()).get();
            goalObj.setAccountId(goal.getAccountId());
            goalObj.setUserId(goal.getUserId());
            goalObj.setCategoryId(goal.getCategoryId());
            goalObj.setGoalName(goal.getGoalName());
            goalObj.setTargetAmount(goal.getTargetAmount());
            goalObj.setGoalStartDate(goal.getGoalStartDate());
            goalObj.setGoalEndDate(goal.getGoalEndDate());
            goalObj.setGoalStatus(goal.getGoalStatus());
            goalObj.setFrequencyOfContribution(goal.getFrequencyOfContribution());
            goalObj.setContributionStyle(goal.getContributionStyle());
            goalObj.setDayOfWeek(goal.getDayOfWeek());
            goalObj.setDateOfMonth(goal.getDateOfMonth());
            goalObj.setContributionAmount(goal.getContributionAmount());
            goalObj.setRoundToNextEuro(goal.getRoundToNextEuro());
            goalObj.setPercentageOfExpense(goal.getPercentageOfExpense());
            //goal.setModifiedAt();
            goalDao.save(goalObj);
            httpResponse.setStatus(SUCCESS);
            httpResponse.setMessage(MSG_FOR_SUCCESSFUL_UPDATE);
            httpResponse.setObject(goalObj);
            logger.info("End of method updating goals");
        } catch (Exception e) {
            logger.error("exception occurred while updating goals : ", e);
            httpResponse.setStatus(FAILURE);
            httpResponse.setMessage(MSG_FOR_FAILED_UPDATE);
        }
        return httpResponse;
    }
}
