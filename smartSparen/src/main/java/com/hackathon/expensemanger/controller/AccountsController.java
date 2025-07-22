package com.hackathon.expensemanger.controller;

import com.hackathon.expensemanger.dao.AccountsDao;
import com.hackathon.expensemanger.entity.Accounts;
import com.hackathon.expensemanger.util.HttpResponse;
import jakarta.persistence.TemporalType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.sql.Timestamp;
import java.time.Instant;
import java.time.temporal.TemporalField;
import java.util.Optional;

import static com.hackathon.expensemanger.util.Constants.*;

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
            if(accounts.getIsConsent())
                accounts.setIsConsent(Boolean.TRUE);
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

    @Modifying
    @Transactional
    @PutMapping("/accounts")
    public HttpResponse updateAccounts(@RequestBody Accounts accounts) {
        HttpResponse<Accounts> httpResponse = new HttpResponse();
        logger.info("Updating Accounts object");
        try {
            Optional<Accounts> accounts1 = accountsDao.findById(accounts.getAccountId().longValue());
            if(accounts1.isPresent()) {
                Accounts updateAccount = accounts1.get();
                if(accounts.getIsConsent()){
                    logger.info("Consent update TRUE");
                    updateAccount.setIsConsent(Boolean.TRUE);
                }else{
                    logger.info("Consent update FALSE");
                    updateAccount.setIsConsent(Boolean.FALSE);
                }
                accountsDao.flush();
                updateAccount.setBalance(accounts.getBalance());
                updateAccount.setUpdatedAt(new Timestamp(System.currentTimeMillis()));
                updateAccount.setCreatedAt(new Timestamp(System.currentTimeMillis()));
                accountsDao.saveAndFlush(updateAccount);
            }
            httpResponse.setStatus(SUCCESS);
            httpResponse.setMessage(MSG_FOR_SUCCESSFUL_INSERTION);
            httpResponse.setObject(accounts);
            logger.info("End of method updating accounts");
        } catch (Exception e) {
            logger.error("exception occurred while inserting accounts : ", e);
            httpResponse.setStatus(FAILURE);
            httpResponse.setMessage(MSG_FOR_FAILED_INSERTION);
        }
        return httpResponse;
    }
}
