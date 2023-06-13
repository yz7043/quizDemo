package com.andy.project1.service.category;


import com.andy.project1.dao.CategoryDao;
import com.andy.project1.dao.QuestionDao;
import com.andy.project1.domain.Category;
import com.andy.project1.domain.Question;
import com.andy.project1.util.BSResult;
import com.andy.project1.util.Constant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

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
        return categoryDao.getAllCategories();
    }
    public List<Category> getAllValidCategory(){
        List<Category> found = categoryDao.getAllCategories();
        List<Category> valid = new LinkedList<>();
        for(Category c : found){
            List<Question> questions = questionDao.getQuestionsByCategory(c.getCategory_id());
            int size = questions.stream().filter(q -> q.getIs_active()).collect(Collectors.toList()).size();
            if(size >= Constant.QUIZ_POOL_SIZE){
                valid.add(c);
            }
        }
        return valid;
    }

    public Category getCategoryById(Integer id){
        return categoryDao.getCategoryById(id);
    }

    public BSResult addNewCategory(String category, String picture){
        if(category == null || category.isEmpty()){
            return new BSResult(false, "Category cannot be empty");
        }
        if(category.length() > Category.CATEGORY_NAME_MAX_LEN){
            return new BSResult(false, "Category length exceeds " + Category.CATEGORY_NAME_MAX_LEN);
        }
        if(picture != null && picture.length() > Category.URL_MAX_LEN){
            return new BSResult(false, "Picture url length exceeds " + Category.URL_MAX_LEN);
        }
        List<Category> allCategories = categoryDao.getAllCategories();
        boolean hasPresented = false;
        for(Category c : allCategories){
            if(c.getName().equals(category)){
                hasPresented = true;
                break;
            }
        }
        if(hasPresented){
            return new BSResult(false, "This category name has been used");
        }
        Category c = new Category();
        c.setName(category);
        if(picture != null && picture.length() > 0)
            c.setPicture(picture);
        categoryDao.addCategory(c);
        return new BSResult(true, "Success");
    }
}
