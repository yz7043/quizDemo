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
        if(category.getPicture() == null){
            String query = "INSERT INTO Category (name) value (?)";
            return jdbcTemplate.update(query, category.getName());
        }else{
            String query = "INSERT INTO Category (name, picture) values (?, ?)";
            return jdbcTemplate.update(query, category.getName(), category.getPicture());
        }
    }

    public int deleteCategoryById(Integer id){
        String query = "DELETE FROM Category WHERE category_id = ?";
        return jdbcTemplate.update(query, id);
    }

    public Category getCategoryById(int id){
        String query = "SELECT * FROM Category WHERE category_id = ?";
        List<Category> found = jdbcTemplate.query(query, categoryRowMapper, id);
        return found.size() == 0 ? null : found.get(0);
    }
}
