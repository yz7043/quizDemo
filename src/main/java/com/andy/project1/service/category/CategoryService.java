package com.andy.project1.service.category;


import com.andy.project1.dao.CategoryDao;
import com.andy.project1.dao.QuestionDao;
import com.andy.project1.domain.Category;
import com.andy.project1.util.Constant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;

@Service
public class CategoryService {
    private final CategoryDao categoryDao;
    private final QuestionDao questionDao;
    @Autowired
    public CategoryService(CategoryDao categoryDao, QuestionDao questionDao) {
        this.categoryDao = categoryDao;
        this.questionDao = questionDao;
    }

    public List<Category> getAllCategory(){
        List<Category> found = categoryDao.getAllCategories();
        List<Category> valid = new LinkedList<>();
        for(Category c : found){
            int size = questionDao.getQuestionsByCategory(c.getCategory_id()).size();
            if(size >= Constant.QUIZ_POOL_SIZE){
                valid.add(c);
            }
        }
        return valid;
    }

    public Category getCategoryById(Integer id){
        return categoryDao.getCategoryById(id);
    }
}
