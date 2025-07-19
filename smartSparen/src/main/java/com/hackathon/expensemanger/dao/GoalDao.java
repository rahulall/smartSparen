package com.hackathon.expensemanger.dao;

import com.hackathon.expensemanger.entity.Goal;
import org.springframework.data.jpa.repository.JpaRepository;

//@Repository
public interface GoalDao extends JpaRepository<Goal, Integer> {
}
