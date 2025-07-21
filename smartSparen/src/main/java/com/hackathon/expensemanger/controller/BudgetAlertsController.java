package com.hackathon.expensemanger.controller;

import com.hackathon.expensemanger.dao.BudgetAlertsDao;
import com.hackathon.expensemanger.entity.BudgetAlerts;
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
public class BudgetAlertsController {

    private static final Logger logger = LoggerFactory.getLogger(BudgetAlertsController.class);

    @Autowired
    private BudgetAlertsDao budgetAlertsDao;

    @PostMapping("/budgetAlerts")
    public HttpResponse insertBudgetAlerts(@RequestBody BudgetAlerts budgetAlerts) {
        HttpResponse<BudgetAlerts> httpResponse = new HttpResponse();
        logger.info("Adding BudgetAlerts object");
        try {
            budgetAlertsDao.save(budgetAlerts);
            httpResponse.setStatus(SUCCESS);
            httpResponse.setMessage(MSG_FOR_SUCCESSFUL_INSERTION);
            httpResponse.setObject(budgetAlerts);
            logger.info("End of method adding BudgetAlerts");
        } catch (Exception e) {
            logger.error("exception occurred while inserting BudgetAlerts : ", e);
            httpResponse.setStatus(FAILURE);
            httpResponse.setMessage(MSG_FOR_FAILED_INSERTION);
        }
        return httpResponse;
    }
}