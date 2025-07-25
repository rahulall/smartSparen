package com.hackathon.expensemanger.bean;

import lombok.Data;

import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Timestamp;

@Data
public class GoalVO {

    private Integer goalId;
    private Integer userId;
    private Integer accountId;
    private Integer categoryId;
    private String goalName;
    private BigDecimal targetAmount;
    private Date goalStartDate;
    private Date goalEndDate;
    private String goalStatus;
    private String frequencyOfContribution;
    private String contributionStyle;
    private String dayOfWeek;
    private Integer dateOfMonth;
    private BigDecimal contributionAmount;
    private BigDecimal roundToNextEuro;
    private BigDecimal percentageOfExpense;
    private String categoryOfExpense;
    private Timestamp createdAt;
    private Timestamp modifiedAt;
}
