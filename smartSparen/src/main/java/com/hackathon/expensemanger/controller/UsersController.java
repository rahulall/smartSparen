package com.hackathon.expensemanger.controller;

import com.hackathon.expensemanger.dao.UsersDao;
import com.hackathon.expensemanger.util.HttpResponse;
import com.hackathon.expensemanger.entity.Users;
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
public class UsersController {

    private static final Logger logger = LoggerFactory.getLogger(UsersController.class);

    @Autowired
    private UsersDao usersDao;

    @PostMapping("/users")
    public HttpResponse insertUsers(@RequestBody Users users) {
        HttpResponse<Users> httpResponse = new HttpResponse();
        logger.info("In method adding users");
        try {
            usersDao.save(users);
            httpResponse.setStatus(SUCCESS);
            httpResponse.setMessage(MSG_FOR_SUCCESSFUL_INSERTION);
            httpResponse.setObject(users);
            logger.info("End of method adding users");
        } catch (Exception e) {
            logger.error("exception occurred while inserting users : ", e);
            httpResponse.setStatus(FAILURE);
            httpResponse.setMessage(MSG_FOR_FAILED_INSERTION);
        }
        return httpResponse;
    }
}
