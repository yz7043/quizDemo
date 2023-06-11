package com.andy.project1.service.admin;

import com.andy.project1.dao.*;
import com.andy.project1.domain.*;
import com.andy.project1.domain.admin.AdminQuizResult;
import com.andy.project1.domain.admin.AdminUser;
import com.andy.project1.util.Constant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.relational.core.sql.In;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import javax.servlet.http.HttpServletRequest;
import java.sql.Timestamp;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

@Service
public class AdminQuizResultMgmtService {
    private final QuizDao quizDao;
    private final QuizQuestionDao quizQuestionDao;
    private final ChoiceDao choiceDao;
    private final UserDao userDao;
    private final CategoryDao categoryDao;

    @Autowired
    public AdminQuizResultMgmtService(QuizDao quizDao, QuizQuestionDao quizQuestionDao, ChoiceDao choiceDao, UserDao userDao, CategoryDao categoryDao) {
        this.quizDao = quizDao;
        this.quizQuestionDao = quizQuestionDao;
        this.choiceDao = choiceDao;
        this.userDao = userDao;
        this.categoryDao = categoryDao;
    }

    public void fillAdminQuizResult(List<AdminQuizResult> adminQuizResults, List<Quiz> allQuiz){
        for(Quiz quiz : allQuiz){
            AdminQuizResult quizResultEntry = new AdminQuizResult();
            List<QuizQuestion> quizQuestions = quizQuestionDao.getQuizQuestionByQuizId(quiz.getQuiz_id());
            // add question and quiz
            quizResultEntry.setQuizQuestions(quizQuestions);
            quizResultEntry.setQuiz(quiz);
            // add user to result
            User user = userDao.getUserById(quiz.getUser_id());
            quizResultEntry.setUser(new AdminUser(user));
            // add category
            Category category = categoryDao.getCategoryById(quiz.getCategory_id());
            quizResultEntry.setCategory(category);
            // add choice and score to result
            Integer curScore = 0;
            quizResultEntry.setChoices(new LinkedList<>());
            for(QuizQuestion quizQuestion : quizQuestions){
                List<Choice> choices = choiceDao.getChoicesByQuestionId(quizQuestion.getQuestion_id());
                quizResultEntry.getChoices().add(choices);
                for(Choice choice : choices){
                    if(choice.getChoice_id() == quizQuestion.getUser_choice_id()
                            && choice.getIs_correct()){
                        curScore ++;
                    }
                }
            }
            quizResultEntry.setScore(curScore);
            adminQuizResults.add(quizResultEntry);
        }
    }

    public List<AdminQuizResult> getAllQuiz(){
        List<Quiz> allQuiz = quizDao.getAllQuiz();
        List<AdminQuizResult> adminQuizResults = new LinkedList<>();
        fillAdminQuizResult(adminQuizResults, allQuiz);
        return adminQuizResults;
    }

    public List<AdminQuizResult> getAdminQuizResult(Map<String, String> params, HttpServletRequest request, Model model){
        List<AdminQuizResult> adminQuizResults = new LinkedList<>();
        List<Quiz> allQuiz;
        if(params.get("category_id") != null){
            Integer categoryId = Integer.valueOf(params.get("category_id"));
            allQuiz = quizDao.getQuizByCategoryId(categoryId);
            Category category = categoryDao.getCategoryById(categoryId);
            model.addAttribute(Constant.ADMIN_QUIZ_RESULT_BY_CATEGORY, category);
        }
        else if(params.get("user_id") != null){
            Integer userId = Integer.valueOf(params.get("user_id"));
            allQuiz = quizDao.getQuizByUserId(userId);
            User user = userDao.getUserById(userId);
            model.addAttribute(Constant.ADMIN_QUIZ_RESULT_BY_USER, user);
        }else{
            allQuiz = quizDao.getAllQuiz();
        }
        fillAdminQuizResult(adminQuizResults, allQuiz);
        Collections.sort(adminQuizResults,
                (quiz1, quiz2) -> {
                    Timestamp t1 = quiz1.getQuiz().getTime_start();
                    Timestamp t2 = quiz2.getQuiz().getTime_start();
                    return t2.compareTo(t1);
                }
        );
        return adminQuizResults;
    }
}
