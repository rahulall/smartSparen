package com.hackathon.expensemanger.entity;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import java.sql.Timestamp;

@Entity
@Data
public class BudgetAlerts {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "BUDGETALERT_SEQ")
    @SequenceGenerator(name = "BUDGETALERT_SEQ", sequenceName = "BUDGETALERT_SEQ", allocationSize = 1)
    private Integer alertId;

    private Integer userId;

    private Integer budgetId;

//e.g. LIMIT_WARNING, UNUSUAL_TRANSACTION
    private String alertType;

    @Column(name = "message")
    private String message;

    @CreationTimestamp
    private Timestamp createdAt;

    @CreationTimestamp
    private Timestamp updatedAt;
}