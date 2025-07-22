package com.hackathon.expensemanger.bean;

import jakarta.persistence.Column;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Timestamp;

@Data
public class Goal {

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
