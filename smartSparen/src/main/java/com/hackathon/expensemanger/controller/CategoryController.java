package com.hackathon.expensemanger.controller;

import com.hackathon.expensemanger.bean.CategoryResponse;
import com.hackathon.expensemanger.bean.ChartResponseObject;
import com.hackathon.expensemanger.dao.CategoryDao;
import com.hackathon.expensemanger.entity.Category;
import com.hackathon.expensemanger.util.HttpResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    public List<CategoryResponse> getBarchartResposne(List responseList) throws IndexOutOfBoundsException{
        List<CategoryResponse> returnList = new ArrayList<>();
        for(int i=0;i <responseList.size();i++){
            CategoryResponse response = new CategoryResponse();
            Object[] val =(Object[])responseList.get(i);
            response.setId(Integer.parseInt(val[0].toString()));
            response.setName(val[1].toString());
            response.setAmount(Double.parseDouble(val[3].toString()));
            response.setDate(val[2].toString());
            response.setInsightData("");
            returnList.add(response);
        }
        return returnList;
    }

    public List<CategoryResponse> getPiechartResposne(List responseList) throws IndexOutOfBoundsException{
        List<CategoryResponse> returnList = new ArrayList<>();
        Map<String, Double> finalMap = new HashMap();
        for (int i=0;i <responseList.size();i++) {
            Object[] val = (Object[]) responseList.get(i);
            String keyVal = val[1].toString();
            if (finalMap.containsKey(keyVal)) {
                finalMap.put(keyVal, finalMap.get(keyVal) + Double.parseDouble(val[3].toString()));
            } else {
                finalMap.put(keyVal, Double.parseDouble(val[3].toString()));
            }
        }
        for (Map.Entry<String, Double> entry : finalMap.entrySet()) {
            CategoryResponse response = new CategoryResponse();
            response.setName(entry.getKey());
            response.setAmount(entry.getValue());
            returnList.add(response);
        }
        return returnList;
    }

    @GetMapping("/getCategoryWiseMothlyDetails")
    public HttpResponse getCategoryWiseDetails() {
        HttpResponse httpResponse = new HttpResponse();
        List resList = categoryDao.findByCategoryMonthlyData();

        httpResponse.setStatus(SUCCESS);
        httpResponse.setMessage(MSG_FOR_SUCCESSFUL_RETRIEVAL);
        List<CategoryResponse> barResponse = getBarchartResposne(resList);
        ChartResponseObject chartResponseObject = new ChartResponseObject();
        chartResponseObject.setBarResponseList(barResponse);
        chartResponseObject.setPieResponseList(getPiechartResposne(resList));
        httpResponse.setObject(chartResponseObject);
        return httpResponse;
    }
}
