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

            @Query(name = "Category.findByCategoryMonthlyData", value = "SELECT e.user_id, c.category_name, EXTRACT(YEAR FROM e.transaction_date) AS year, " +
                    "TO_CHAR(e.transaction_date, 'Month') AS month, SUM(e.amount) AS total_expense " +
                    "FROM smart_sparen.expenses e JOIN "+
                    "smart_sparen.category c ON e.category_id = c.category_id "+
                    "WHERE e.transaction_date >= DATE_TRUNC('month', CURRENT_DATE) - INTERVAL '1 month' "+
                    "AND e.transaction_date < DATE_TRUNC('month', CURRENT_DATE) + INTERVAL '1 month' "+
                    "GROUP BY e.user_id, c.category_name, year, month "+
        "ORDER BY e.user_id, year, month, c.category_name", nativeQuery = true)
            public List findByCategoryMonthlyData();
}
