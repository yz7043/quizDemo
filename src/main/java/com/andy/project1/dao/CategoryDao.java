package com.andy.project1.dao;

import com.andy.project1.dao.rowmapper.CategoryRowMapper;
import com.andy.project1.domain.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.relational.core.sql.In;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CategoryDao {
    private final JdbcTemplate jdbcTemplate;
    private final CategoryRowMapper categoryRowMapper;

    @Autowired
    public CategoryDao(JdbcTemplate jdbcTemplate, CategoryRowMapper categoryRowMapper){
        this.jdbcTemplate = jdbcTemplate;
        this.categoryRowMapper = categoryRowMapper;
    }

    public List<Category> getAllCategories(){
        String query = "SELECT * FROM Category";
        return jdbcTemplate.query(query, categoryRowMapper);
    }

    public int addCategory(Category category){
        String query = "INSERT INTO Category (name) value (?)";
        return jdbcTemplate.update(query, category.getName());
    }

    public int deleteCategoryById(Integer id){
        String query = "DELETE FROM Category WHERE category_id = ?";
        return jdbcTemplate.update(query, id);
    }
}
