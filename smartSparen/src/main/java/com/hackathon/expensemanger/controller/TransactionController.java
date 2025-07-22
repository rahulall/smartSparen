package com.hackathon.expensemanger.controller;

import com.hackathon.expensemanger.dao.TransactionDao;
import com.hackathon.expensemanger.entity.Accounts;
import com.hackathon.expensemanger.entity.GoalTransaction;
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
@RequestMapping("/transaction-mgmt")
public class TransactionController {

    private static final Logger logger = LoggerFactory.getLogger(AccountsController.class);

    @Autowired
    private TransactionDao transactionDao;

    @PostMapping("/transaction")
    public HttpResponse insertTransaction(@RequestBody GoalTransaction goalTransaction) {
        HttpResponse<GoalTransaction> httpResponse = new HttpResponse();
        logger.info("Adding Accounts object");
        try {
            transactionDao.save(goalTransaction);
            httpResponse.setStatus(SUCCESS);
            httpResponse.setMessage(MSG_FOR_SUCCESSFUL_INSERTION);
            httpResponse.setObject(goalTransaction);
            logger.info("End of method adding accounts");
        } catch (Exception e) {
            logger.error("exception occurred while inserting accounts : ", e);
            httpResponse.setStatus(FAILURE);
            httpResponse.setMessage(MSG_FOR_FAILED_INSERTION);
        }
        return httpResponse;
    }
}
