package com.annotations.demo.controller;

import com.annotations.demo.dao.CategoryDao;
import com.annotations.demo.entity.Category;
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
public class CategoryController {
    private static final Logger logger = LoggerFactory.getLogger(CategoryController.class);

    @Autowired
    CategoryDao categoryDao;

    @PostMapping("/category")
    public HttpResponse insertGoals(@RequestBody Category category) {
        HttpResponse<Category> httpResponse = new HttpResponse();
        logger.info("Adding category object");
        try {
            categoryDao.save(category);
            httpResponse.setStatus(SUCCESS);
            httpResponse.setMessage(MSG_FOR_SUCCESSFUL_INSERTION);
            httpResponse.setObject(category);
            logger.info("End of method adding category");
        } catch (Exception e) {
            logger.error("exception occurred while inserting category : ", e);
            httpResponse.setStatus(FAILURE);
            httpResponse.setMessage(MSG_FOR_FAILED_INSERTION);
        }
        return httpResponse;
    }
}
