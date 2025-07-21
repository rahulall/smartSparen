package com.hackathon.expensemanger.dao;

import com.hackathon.expensemanger.entity.BudgetAlerts;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BudgetAlertsDao extends JpaRepository<BudgetAlerts, Long> {
}
