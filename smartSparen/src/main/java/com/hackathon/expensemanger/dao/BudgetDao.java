package com.hackathon.expensemanger.dao;

import com.hackathon.expensemanger.entity.Budget;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BudgetDao extends JpaRepository<Budget, Long> {
}
