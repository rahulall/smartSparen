package com.annotations.demo.entity;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import java.math.BigDecimal;
import java.sql.Timestamp;

@Entity
@Data
public class Accounts {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ACCOUNTS_SEQ")
    @SequenceGenerator(name = "ACCOUNTS_SEQ", sequenceName = "ACCOUNTS_SEQ", allocationSize = 1)
    Integer accountId;

    Integer userId;

    String accountName;

    String accountType;

    @Column(name = "balance", precision = 12, scale = 2)
    BigDecimal balance;

    @CreationTimestamp
    Timestamp createdAt;

    @CreationTimestamp
    Timestamp updatedAt;
}
