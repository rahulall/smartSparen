package com.hackathon.expensemanger.controller;

import com.hackathon.expensemanger.dao.AccountsDao;
import com.hackathon.expensemanger.dao.CategoryDao;
import com.hackathon.expensemanger.dao.GoalDao;
import com.hackathon.expensemanger.entity.Goal;
import com.hackathon.expensemanger.bean.GoalVO;
import com.hackathon.expensemanger.util.HttpResponse;
import jakarta.websocket.server.PathParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

import static com.hackathon.expensemanger.util.Constants.*;
import static com.hackathon.expensemanger.util.Constants.MSG_FOR_FAILED_INSERTION;

@RestController
@RequestMapping("/micro-saving-goal")
public class GoalsController {
    private static final Logger logger = LoggerFactory.getLogger(GoalsController.class);

    @Autowired
    private GoalDao goalDao;
    @Autowired
    private AccountsDao accountsDao;
    @Autowired
    private CategoryDao categoryDao;

    @GetMapping("/fetchGoals")
    public List<Goal> fetchGoal(@RequestParam Integer userId){
        logger.info("Updating Goals object");
        List<Goal> goals = null;
        try {
            goals = goalDao.fetchGoalsByUserId(userId);
            logger.info("End of method fetching goals");
        } catch (Exception e) {
            logger.error("exception occurred while updating goals : ", e);
        }
        return goals;
    }

    @GetMapping("/fetchGoalByGoalId")
    public GoalVO fetchGoalByGoalId(@RequestParam Integer goalId){
        Goal goal = null;
        goal = goalDao.findById(goalId).get();
        GoalVO goalVo = populateGoalVo(goal);
        String categoryName = categoryDao.getCategoryNameById(goal.getCategoryId());
        goalVo.setCategoryOfExpense(categoryName);
        return goalVo;
    }

    private GoalVO populateGoalVo(Goal goal){
        GoalVO goalVO = new GoalVO();
        goalVO.setUserId(goal.getUserId());
        goalVO.setAccountId(goal.getAccountId());
        goalVO.setCategoryId(goal.getCategoryId());
        goalVO.setGoalName(goal.getGoalName());
        goalVO.setTargetAmount(goal.getTargetAmount());
        goalVO.setGoalStartDate(goal.getGoalStartDate());
        goalVO.setGoalEndDate(goal.getGoalEndDate());
        goalVO.setGoalStatus(goal.getGoalStatus());
        goalVO.setFrequencyOfContribution(goal.getFrequencyOfContribution());
        goalVO.setContributionStyle(goal.getContributionStyle());
        goalVO.setDayOfWeek(goal.getDayOfWeek());
        goalVO.setDateOfMonth(goal.getDateOfMonth());
        goalVO.setContributionAmount(goal.getContributionAmount());
        goalVO.setRoundToNextEuro(goal.getRoundToNextEuro());
        BigDecimal percentage = (goal.getContributionAmount().divide(goal.getTargetAmount())).multiply(new BigDecimal(100));
        goalVO.setPercentageOfExpense(percentage);
        return goalVO;
    }
    @PostMapping("/insertGoal")
    public void insertGoal(@RequestBody GoalVO goal) {
        HttpResponse<Goal> httpResponse = new HttpResponse<>();
        logger.info("Adding Goals object");
        try {
            goal.setAccountId(accountsDao.findById(goal.getUserId().longValue()).get().getAccountId());
            goal.setCategoryId(categoryDao.findByCategoryName(goal.getCategoryOfExpense()));
            goal.setGoalStatus("ACTIVE");
            goal.setModifiedAt(null);
            Goal goalObj = populateGoalDto(goal);
            goalObj.setModifiedAt(goal.getModifiedAt());
            goalDao.save(goalObj);
            httpResponse.setStatus(SUCCESS);
            httpResponse.setMessage(MSG_FOR_SUCCESSFUL_INSERTION);
            //httpResponse.setObject(goalObj);
            logger.info("End of method adding goals");
        } catch (Exception e) {
            logger.error("exception occurred while inserting goals : ", e);
            httpResponse.setStatus(FAILURE);
            httpResponse.setMessage(MSG_FOR_FAILED_INSERTION);
        }
      //  return httpResponse;
    }
    private Goal populateGoalDto(GoalVO goalVO){
        Goal goal = new Goal();
        goal.setUserId(goalVO.getUserId());
        goal.setAccountId(goalVO.getAccountId());
        goal.setCategoryId(goalVO.getCategoryId());
        goal.setGoalName(goalVO.getGoalName());
        goal.setTargetAmount(goalVO.getTargetAmount());
        goal.setGoalStartDate(goalVO.getGoalStartDate());
        goal.setGoalEndDate(goalVO.getGoalEndDate());
        goal.setGoalStatus(goalVO.getGoalStatus());
        goal.setFrequencyOfContribution(goalVO.getFrequencyOfContribution());
        goal.setContributionStyle(goalVO.getContributionStyle());
        goal.setDayOfWeek(goalVO.getDayOfWeek());
        goal.setDateOfMonth(goalVO.getDateOfMonth());
        goal.setContributionAmount(goalVO.getContributionAmount());
        goal.setRoundToNextEuro(goalVO.getRoundToNextEuro());
        BigDecimal percentage = (goalVO.getContributionAmount().divide(goalVO.getTargetAmount())).multiply(new BigDecimal(100));
        goal.setPercentageOfExpense(percentage);
        goal.setCategoryOfExpense(goalVO.getCategoryOfExpense());
        return goal;
    }

    @PostMapping("/updateGoal")
    public HttpResponse<Goal> updateGoal(@RequestBody GoalVO goal) {
        HttpResponse<Goal> httpResponse = new HttpResponse<>();
        logger.info("Updating Goals object");
            Goal goalObj = new Goal();
        try {
           // goalObj = goalDao.findById(goal.getGoalId()).get();
            goalObj.setGoalId(goal.getGoalId());
            goalObj.setAccountId(goal.getAccountId());
            goalObj.setUserId(goal.getUserId());
            goalObj.setCategoryId(goal.getCategoryId());
            goalObj.setGoalName(goal.getGoalName());
            goalObj.setTargetAmount(goal.getTargetAmount());
            //goalObj.setGoalStartDate(goal.getGoalStartDate());
            goalObj.setGoalEndDate(goal.getGoalEndDate());
            goalObj.setGoalStatus(goal.getGoalStatus());
            goalObj.setFrequencyOfContribution(goal.getFrequencyOfContribution());
            goalObj.setContributionStyle(goal.getContributionStyle());
            goalObj.setDayOfWeek(goal.getDayOfWeek());
            goalObj.setDateOfMonth(goal.getDateOfMonth());
            goalObj.setContributionAmount(goal.getContributionAmount());
            //goalObj.setRoundToNextEuro(goal.getRoundToNextEuro());
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
