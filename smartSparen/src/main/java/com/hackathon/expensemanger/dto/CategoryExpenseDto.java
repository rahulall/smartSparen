package com.hackathon.expensemanger.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;
import java.sql.Date;


@Data
@AllArgsConstructor
public class CategoryExpenseDto {
    String name;
    String categoryName;
    BigDecimal amount;
    String description;
    Date transactionDate;
}
