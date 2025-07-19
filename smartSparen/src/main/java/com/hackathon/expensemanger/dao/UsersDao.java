package com.hackathon.expensemanger.dao;

import com.hackathon.expensemanger.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsersDao extends JpaRepository<Users, Long> {
}
