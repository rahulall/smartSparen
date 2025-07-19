package com.annotations.demo.dao;

import com.annotations.demo.entity.Accounts;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountsDao extends JpaRepository<Accounts, Long> {
}
