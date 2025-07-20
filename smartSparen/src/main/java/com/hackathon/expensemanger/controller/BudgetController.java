package com.hackathon.expensemanger.controller;

import com.hackathon.expensemanger.dao.BudgetDao;
import com.hackathon.expensemanger.entity.Budget;
import com.hackathon.expensemanger.util.HttpResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.hackathon.expensemanger.util.Constants.*;

@RestController
@RequestMapping("/expensemanagement")
public class BudgetController {

    private static final Logger logger = LoggerFactory.getLogger(BudgetController.class);

    @Autowired
    private BudgetDao budgetDao;

    @PostMapping("/budget")
    public HttpResponse insertBudget(@RequestBody Budget budget) {
        HttpResponse<Budget> httpResponse = new HttpResponse();
        logger.info("Adding Budget object");
        try {
            budgetDao.save(budget);
            httpResponse.setStatus(SUCCESS);
            httpResponse.setMessage(MSG_FOR_SUCCESSFUL_INSERTION);
            httpResponse.setObject(budget);
            logger.info("End of method adding Budget");
        } catch (Exception e) {
            logger.error("exception occurred while inserting Budget : ", e);
            httpResponse.setStatus(FAILURE);
            httpResponse.setMessage(MSG_FOR_FAILED_INSERTION);
        }
        return httpResponse;
    }
}
