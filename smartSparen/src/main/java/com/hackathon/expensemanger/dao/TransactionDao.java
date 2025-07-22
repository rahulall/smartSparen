package com.hackathon.expensemanger.dao;

import com.hackathon.expensemanger.entity.GoalTransaction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionDao extends JpaRepository<GoalTransaction, Long> {
}
