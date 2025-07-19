package com.hackathon.expensemanger.dao;

import com.hackathon.expensemanger.entity.Accounts;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountsDao extends JpaRepository<Accounts, Long> {
}
