package com.andy.project1.service.category;


import com.andy.project1.dao.CategoryDao;
import com.andy.project1.domain.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.relational.core.sql.In;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {
    private final CategoryDao categoryDao;

    @Autowired
    public CategoryService(CategoryDao categoryDao) {
        this.categoryDao = categoryDao;
    }

    public List<Category> getAllCategory(){
        return categoryDao.getAllCategories();
    }

    public Category getCategoryById(Integer id){
        return categoryDao.getCategoryById(id);
    }
}
