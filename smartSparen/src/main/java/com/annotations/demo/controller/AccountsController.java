package com.annotations.demo.controller;

import com.annotations.demo.dao.AccountsDao;
import com.annotations.demo.entity.Accounts;
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
public class AccountsController {

    private static final Logger logger = LoggerFactory.getLogger(AccountsController.class);

    @Autowired
    private AccountsDao accountsDao;

    @PostMapping("/accounts")
    public HttpResponse insertAccounts(@RequestBody Accounts accounts) {
        HttpResponse<Accounts> httpResponse = new HttpResponse();
        logger.info("Adding Accounts object");
        try {
            accountsDao.save(accounts);
            httpResponse.setStatus(SUCCESS);
            httpResponse.setMessage(MSG_FOR_SUCCESSFUL_INSERTION);
            httpResponse.setObject(accounts);
            logger.info("End of method adding accounts");
        } catch (Exception e) {
            logger.error("exception occurred while inserting accounts : ", e);
            httpResponse.setStatus(FAILURE);
            httpResponse.setMessage(MSG_FOR_FAILED_INSERTION);
        }
        return httpResponse;
    }
}
