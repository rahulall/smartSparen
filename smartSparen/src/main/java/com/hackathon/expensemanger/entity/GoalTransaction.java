package com.hackathon.expensemanger.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.math.BigDecimal;
import java.sql.Timestamp;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class GoalTransaction {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "GOALTRANSACTION_SEQ")
    @SequenceGenerator(name = "GOALTRANSACTION_SEQ", sequenceName = "GOALTRANSACTION_SEQ", allocationSize = 1)
    private Integer goalTransactionId;

    private Integer goalId;

    @Column(name = "transaction_amount", precision = 15,scale = 2)
    private BigDecimal transactionAmount;

    @Column(name = "transaction_goal_amount", precision = 15,scale = 2)
    private BigDecimal transactionGoalAmount;

    @CreationTimestamp
    private Timestamp createdTime;

    @CreationTimestamp
    private Timestamp updatedAt;

}
