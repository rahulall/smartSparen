package com.hackathon.expensemanger.entity;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.SourceType;
import org.hibernate.annotations.UpdateTimestamp;

import java.math.BigDecimal;
import java.sql.Timestamp;

@Entity
@Data
public class Accounts {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ACCOUNTS_SEQ")
    @SequenceGenerator(name = "ACCOUNTS_SEQ", sequenceName = "ACCOUNTS_SEQ", allocationSize = 1)
    private Integer accountId;

    private Integer userId;

    private String accountName;

    private String accountType;

    @Column(name = "balance", precision = 12, scale = 2)
    private BigDecimal balance;

    @UpdateTimestamp
    private Timestamp createdAt;

    @UpdateTimestamp
    private Timestamp updatedAt;

    @Column(name = "is_consent")
    private Boolean isConsent;
}
