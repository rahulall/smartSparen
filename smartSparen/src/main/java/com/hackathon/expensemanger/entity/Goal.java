package com.hackathon.expensemanger.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

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
    @Column(name = "user_id")
    private Integer userId;
    @Column(name = "account_id")
    private Integer accountId;
    @Column(name = "category_id")
    private Integer categoryId;
    @Column(name = "goal_name")
    private String goalName;
    @Column(name = "target_amount", precision = 15, scale = 2)
    private BigDecimal targetAmount;
    @Column(name = "goal_start_date")
    private Date goalStartDate;
    @Column(name = "goal_end_date")
    private Date goalEndDate;
    @Column(name = "goal_status")
    private String goalStatus;
    @Column(name = "frequency_of_contribution")
    private String frequencyOfContribution;
    @Column(name = "contribution_style")
    private String contributionStyle;
    @Column(name = "day_of_week")
    private String dayOfWeek;
    @Column(name = "date_of_month")
    private Integer dateOfMonth;
    @Column(name = "contribution_amount", precision = 15, scale = 2)
    private BigDecimal contributionAmount;
    @Column(name = "round_to_next_euro", precision = 15, scale = 2)
    private BigDecimal roundToNextEuro;
    @Column(name = "percentage_of_expense", precision = 5, scale = 2)
    private BigDecimal percentageOfExpense;
    @Column(name = "category_of_expense")
    private String categoryOfExpense;
    @CreationTimestamp
    @Column(name="created_date")
    private Timestamp createdAt;
    @UpdateTimestamp
    @Column(name= "modified_date")
    private Timestamp modifiedAt;
}
