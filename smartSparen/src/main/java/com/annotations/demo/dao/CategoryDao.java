package com.annotations.demo.dao;

import com.annotations.demo.entity.Accounts;
import com.annotations.demo.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryDao extends JpaRepository<Category, Long> {
}
