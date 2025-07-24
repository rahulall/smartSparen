package com.hackathon.expensemanger.dao;

import com.hackathon.expensemanger.dto.CategoryExpenseDto;
import com.hackathon.expensemanger.entity.Expenses;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ExpensesDao extends JpaRepository<Expenses, Long> {
    @Query(value = "SELECT " +
            "new com.hackathon.expensemanger.dto.CategoryExpenseDto(u.name,c.categoryName,e.amount,e.description,e.transactionDate" +
            ")  FROM " +
            "Expenses e JOIN " +
            "Users u ON u.userId=e.userId JOIN " +
            "Category c ON c.categoryId=e.categoryId WHERE c.categoryName=?1 ", nativeQuery = false)
    public List<CategoryExpenseDto> findDataCategorywise(String category);
}
