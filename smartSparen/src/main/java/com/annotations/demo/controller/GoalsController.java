package com.annotations.demo.controller;

import com.annotations.demo.dao.GoalDao;
import com.annotations.demo.entity.Goal;
import com.annotations.demo.util.HttpResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.annotations.demo.util.Constants.*;
import static com.annotations.demo.util.Constants.MSG_FOR_FAILED_INSERTION;

@RestController
@RequestMapping("/expensemanagement")
public class GoalsController {
    private static final Logger logger = LoggerFactory.getLogger(GoalsController.class);

    @Autowired
    private GoalDao goalDao;

    @PostMapping("/goal")
    public HttpResponse insertGoals(@RequestBody Goal goal) {
        HttpResponse<Goal> httpResponse = new HttpResponse();
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
}
