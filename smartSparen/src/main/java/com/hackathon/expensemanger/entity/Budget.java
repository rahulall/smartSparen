package com.hackathon.expensemanger.entity;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Timestamp;

@Entity
@Data
public class Budget {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "BUDGET_SEQ")
    @SequenceGenerator(name = "BUDGET_SEQ", sequenceName = "BUDGET_SEQ", allocationSize = 1)
    private Integer budgetId;

    private Integer userId;

    //'weekly', 'monthly', 'yearly'
    private String periodType;

    private Date startDate;

    private Date endDate;

    //0.9 default value
    @Column(name = "alert_threshold", precision = 5, scale = 2)
    private BigDecimal alertThreshold;

    @CreationTimestamp
    private Timestamp createdAt;

    @CreationTimestamp
    private Timestamp updatedAt;
}
