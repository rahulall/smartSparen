package com.hackathon.expensemanger.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Timestamp;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Goal {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "GOAL_SEQ")
    @SequenceGenerator(name = "GOAL_SEQ", sequenceName = "GOAL_SEQ", allocationSize = 1)
    private Integer goalId;

    private Integer userId;

    private Integer accountId;

    private Integer categoryId;

    private String goalName;

    @Column(name = "target_amount", precision = 12, scale = 2)
    private BigDecimal targetAmount;

    private Date goalStartDate;

    private Date goalEndDate;

    private String goalStatus;

    private String frequencyOfContribution;

    private String contributionStyle;

    private String dayOfWeek;

    private Integer dateOfMonth;

    @Column(name = "contribution_amount", precision = 12, scale = 2)
    private BigDecimal contributionAmount;

    @Column(name = "round_to_next_euro", precision = 12, scale = 2)
    private BigDecimal roundToNextEuro;

    @Column(name = "percentage_of_expense", precision = 12, scale = 2)
    private BigDecimal percentageOfExpense;

    private String categoryOfExpense;

    @CreationTimestamp
    private Timestamp createdAt;

}
