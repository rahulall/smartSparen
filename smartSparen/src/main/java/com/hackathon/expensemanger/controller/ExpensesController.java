package com.hackathon.expensemanger.controller;

import com.hackathon.expensemanger.dao.ExpensesDao;
import com.hackathon.expensemanger.entity.Expenses;
import com.hackathon.expensemanger.entity.Goal;
import com.hackathon.expensemanger.util.HttpResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.hackathon.expensemanger.util.Constants.*;
import static com.hackathon.expensemanger.util.Constants.MSG_FOR_FAILED_INSERTION;

@RestController
@RequestMapping("/expensemanagement")
public class ExpensesController {

    private static final Logger logger = LoggerFactory.getLogger(ExpensesController.class);

    @Autowired
    private ExpensesDao expensesDao;

    @PostMapping("/expenses")
    public HttpResponse insertExpenses(@RequestBody Expenses expenses) {
        HttpResponse<Expenses> httpResponse = new HttpResponse();
        logger.info("Adding Expenses object");
        try {
            expensesDao.save(expenses);
            httpResponse.setStatus(SUCCESS);
            httpResponse.setMessage(MSG_FOR_SUCCESSFUL_INSERTION);
            httpResponse.setObject(expenses);
            logger.info("End of method adding Expenses");
        } catch (Exception e) {
            logger.error("exception occurred while inserting Expenses : ", e);
            httpResponse.setStatus(FAILURE);
            httpResponse.setMessage(MSG_FOR_FAILED_INSERTION);
        }
        return httpResponse;
    }
}
