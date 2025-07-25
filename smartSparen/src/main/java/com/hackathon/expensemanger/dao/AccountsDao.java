package com.hackathon.expensemanger.dao;

import com.hackathon.expensemanger.entity.Accounts;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountsDao extends JpaRepository<Accounts, Long> {

    @Query(name = "Accounts.findAccountsByUserId", value = "Select * from Accounts a where a.user_id = :userId" , nativeQuery = true)
    public Accounts getAccountsByUserId(Integer userId);
}
