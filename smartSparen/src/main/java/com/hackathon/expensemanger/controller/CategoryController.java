package com.hackathon.expensemanger.controller;

import com.hackathon.expensemanger.dao.CategoryDao;
import com.hackathon.expensemanger.entity.Category;
import com.hackathon.expensemanger.util.HttpResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.hackathon.expensemanger.util.Constants.*;
import static com.hackathon.expensemanger.util.Constants.MSG_FOR_FAILED_INSERTION;

@RestController
@RequestMapping("/expensemanagement")
public class CategoryController {
    private static final Logger logger = LoggerFactory.getLogger(CategoryController.class);

    @Autowired
    private CategoryDao categoryDao;

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

    @GetMapping("/getCategoryWiseMothlyDetails")
    public HttpResponse getCategoryWiseDetails() {
        HttpResponse httpResponse = new HttpResponse();
        List resList = categoryDao.findByCategoryMonthlyData();
        httpResponse.setStatus(SUCCESS);
        httpResponse.setMessage(MSG_FOR_SUCCESSFUL_RETRIEVAL);
        httpResponse.setObject(resList);
        return httpResponse;
    }
}
