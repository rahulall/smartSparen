package com.hackathon.expensemanger.controller;

import com.hackathon.expensemanger.dao.CategoryDao;
import com.hackathon.expensemanger.dao.ExpensesDao;
import com.hackathon.expensemanger.dto.CategoryExpenseDto;
import com.hackathon.expensemanger.entity.Category;
import com.hackathon.expensemanger.entity.Expenses;
import com.hackathon.expensemanger.util.HttpResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static com.hackathon.expensemanger.util.Constants.*;
import static com.hackathon.expensemanger.util.Constants.MSG_FOR_FAILED_INSERTION;

@RestController
@RequestMapping("/expensemanagement")
public class ExpensesController {

    private static final Logger logger = LoggerFactory.getLogger(ExpensesController.class);

    @Autowired
    private ExpensesDao expensesDao;

    @Autowired
    private CategoryDao categoryDao;

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

    @GetMapping("/getexpenses/{category}/{frequency}")
    public HttpResponse getExpenses(@PathVariable String category, @PathVariable String frequency){
        HttpResponse<List<CategoryExpenseDto>> httpResponse = new HttpResponse();
        if(!StringUtils.isEmpty(category)){
            LocalDate startDate = LocalDate.now();
            LocalDate endDate = startDate.minusMonths(1);
            if(frequency.equals("Weekly")){
                startDate = LocalDate.now();
                endDate = startDate.minusWeeks(1);
            }
            Date stDate = Date.valueOf(startDate);
            Date enDate = Date.valueOf(endDate);
            List<CategoryExpenseDto> categoryExpenseDto = new ArrayList<>();
            if (category.equals("All")){
                expensesDao.findAllData(stDate, enDate);
            } else {
                expensesDao.findDataCategorywise(stDate, enDate, category);
            }
            httpResponse.setMessage(SUCCESS);
            httpResponse.setStatus(MSG_FOR_SUCCESSFUL_GET_CATEGORY_WISE_RETRIEVAL);
            httpResponse.setObject(categoryExpenseDto);
        }else {
            httpResponse.setMessage(FAILURE);
            httpResponse.setStatus(MSG_FOR_FAILURE_GET_CATEGORY_WISE_RETRIEVAL);
        }
        return httpResponse;
    }

}
