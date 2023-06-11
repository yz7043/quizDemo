package com.andy.project1.service.admin;

import com.andy.project1.dao.CategoryDao;
import com.andy.project1.dao.QuestionDao;
import com.andy.project1.dao.QuizDao;
import com.andy.project1.domain.Category;
import com.andy.project1.domain.Question;
import com.andy.project1.domain.Quiz;
import com.andy.project1.domain.admin.AdminQuestion;
import com.andy.project1.util.BSResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AdminQuestionMgmtService {
    private final QuestionDao questionDao;
    private final CategoryDao categoryDao;

    @Autowired
    public AdminQuestionMgmtService(QuestionDao questionDao, CategoryDao categoryDao) {
        this.questionDao = questionDao;
        this.categoryDao = categoryDao;
    }

    public List<AdminQuestion> getQuestionWithCategory(){
        List<Question> allQuestions = questionDao.getAllQuestions();
        List<AdminQuestion> result = allQuestions.stream().map(
                question -> {
                    Category category = categoryDao.getCategoryById(question.getCategory_id());
                    return new AdminQuestion(question, category);
                }
        ).collect(Collectors.toList());
        return result;
    }

    @Transactional
    public BSResult toggleAQuestion(int questionId){
        Question question = questionDao.getQuestionById(questionId);
        BSResult result = new BSResult();

        if(question.getIs_active()){
            // Check if the category more than 5 question
            List<Question> questions = questionDao.getQuestionsByCategory(question.getCategory_id())
                    .stream().filter(q -> q.getIs_active()).collect(Collectors.toList());
            if(questions.size() <= 5){
                result.setSuccess(false);
                result.setMsg("Question Pool for this category must contain more than 5 active questions");
            }else{
                question.setIs_active(!question.getIs_active());
                questionDao.updateQuestion(question);
                result.setSuccess(true);
                result.setMsg("Success");
            }
        }else{
            question.setIs_active(!question.getIs_active());
            questionDao.updateQuestion(question);
            result.setSuccess(true);
            result.setMsg("Success");
        }
        return result;
    }
}
