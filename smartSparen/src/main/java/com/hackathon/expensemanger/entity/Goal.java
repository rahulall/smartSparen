package com.hackathon.expensemanger.entity;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import java.math.BigDecimal;
import java.sql.Timestamp;

@Entity
@Data
public class Goal {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "GOAL_SEQ")
    @SequenceGenerator(name = "GOAL_SEQ", sequenceName = "GOAL_SEQ", allocationSize = 1)
    private Integer goalId;

    private String goalType;

    @Column(name = "goal_amount", precision = 12, scale = 2)
    private BigDecimal goalAmount;

    @Column(name = "goal_achieved_amount", precision = 12, scale = 2)
    private BigDecimal goalAchievedAmount;

    private Integer goalPercentAchieved;

    private Integer goalAccountId;

    @CreationTimestamp
    private Timestamp createdAt;

    @CreationTimestamp
    private Timestamp updatedAt;
}
