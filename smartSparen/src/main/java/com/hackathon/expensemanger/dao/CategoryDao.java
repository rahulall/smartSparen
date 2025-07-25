package com.hackathon.expensemanger.dao;

import com.hackathon.expensemanger.entity.Category;
import jakarta.persistence.NamedQueries;
import jakarta.persistence.NamedQuery;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoryDao extends JpaRepository<Category, Long> {

           /* @Query(name = "Category.findByCategoryMonthlyData", value = "SELECT e.user_id, c.category_name, EXTRACT(YEAR FROM e.transaction_date) AS year, " +
                    "TO_CHAR(e.transaction_date, 'Month') AS month, SUM(e.amount) AS total_expense " +
                    "FROM smart_sparen.expenses e JOIN "+
                    "smart_sparen.category c ON e.category_id = c.category_id "+
                    "WHERE e.transaction_date >= DATE_TRUNC('month', CURRENT_DATE) - INTERVAL '1 month' "+
                    "AND e.transaction_date < DATE_TRUNC('month', CURRENT_DATE) + INTERVAL '1 month' "+
                    "GROUP BY e.user_id, c.category_name, year, month "+
        "ORDER BY e.user_id, year, month, c.category_name", nativeQuery = true)
            public List findByCategoryMonthlyData();*/

    @Query(name = "Category.findByCategoryMonthlyData", value = "SELECT user_id, category_name, TO_CHAR(month_start, 'YYYY-MM') AS month, total_expense, "+
            "CASE "+
            "WHEN previous_month_expense IS NULL THEN 'no data' "+
            "WHEN total_expense > previous_month_expense THEN 'more' "+
            "WHEN total_expense < previous_month_expense THEN 'low' "+
            "ELSE 'same' "+
            "END AS insight "+
            "FROM ( "+
            "SELECT "+
            "e.user_id, c.category_name, DATE_TRUNC('month', e.transaction_date) AS month_start, "+
            "SUM(e.amount) AS total_expense, LAG(SUM(e.amount)) OVER (PARTITION BY e.user_id, c.category_name "+
            "ORDER BY DATE_TRUNC('month', e.transaction_date)) AS previous_month_expense "+
            "FROM "+
            "smart_sparen.expenses e "+
            "JOIN "+
            "smart_sparen.category c ON e.category_id = c.category_id "+
            "WHERE "+
            "DATE_TRUNC('month', e.transaction_date) >= DATE_TRUNC('month', CURRENT_DATE) - INTERVAL '1 month' "+
            "AND DATE_TRUNC('month', e.transaction_date) <= DATE_TRUNC('month', CURRENT_DATE) "+
            "GROUP BY "+
            "e.user_id, "+
            "c.category_name, "+
            "DATE_TRUNC('month', e.transaction_date) "+
            ") AS sub ORDER BY user_id,category_name, month_start", nativeQuery = true)
    public List findByCategoryMonthlyData();

    @Query(name = "Category.findByCategoryName", value = "Select * from Category c where c.category_name = :categoryName" , nativeQuery = true)
    public int findByCategoryName(String categoryName);
    @Query(name = "Category.getCategoryNameById", value = "Select c.category_name from Category c where c.category_id = :id" , nativeQuery = true)
    public String getCategoryNameById(Integer id);


}
