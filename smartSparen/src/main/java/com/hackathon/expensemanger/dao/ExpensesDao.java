package com.hackathon.expensemanger.dao;

import com.hackathon.expensemanger.entity.Expenses;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ExpensesDao extends JpaRepository<Expenses, Long> {
}
