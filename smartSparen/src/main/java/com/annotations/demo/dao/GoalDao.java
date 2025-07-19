package com.annotations.demo.dao;

import com.annotations.demo.entity.Goal;
import org.springframework.data.jpa.repository.JpaRepository;

//@Repository
public interface GoalDao extends JpaRepository<Goal, Integer> {
}
