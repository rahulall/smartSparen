package com.hackathon.expensemanger.dao;

import com.hackathon.expensemanger.entity.Goal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

//@Repository
public interface GoalDao extends JpaRepository<Goal, Integer> {
    @Query(name = "Goal.fetchGoalsByUserId", value = "Select * from Goal g where g.user_id = :userId" , nativeQuery = true)
    public List<Goal> fetchGoalsByUserId(Integer userId);
}
