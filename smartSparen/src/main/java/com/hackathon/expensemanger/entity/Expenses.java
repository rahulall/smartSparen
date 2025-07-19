package com.hackathon.expensemanger.entity;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Timestamp;

@Data
@Entity
public class Expenses {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "EXPENSES_SEQ")
    @SequenceGenerator(name = "EXPENSES_SEQ", sequenceName = "EXPENSES_SEQ", allocationSize = 1)
    private Integer expenseId;

    private Integer userId;

    private Integer accountId;

    private Integer categoryId;

    @Column(name = "amount", precision = 12,scale = 2)
    private BigDecimal amount;

    private String description;

    private String merchant;

    private Date transactionDate;

    private boolean isManualTag;

    @CreationTimestamp
    private Timestamp createdAt;

    @CreationTimestamp
    private Timestamp updatedAt;
}
