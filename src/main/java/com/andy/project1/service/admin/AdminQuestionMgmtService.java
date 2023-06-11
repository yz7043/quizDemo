package com.andy.project1.service.admin;

import com.andy.project1.dao.CategoryDao;
import com.andy.project1.dao.ChoiceDao;
import com.andy.project1.dao.QuestionDao;
import com.andy.project1.domain.Category;
import com.andy.project1.domain.Choice;
import com.andy.project1.domain.Question;
import com.andy.project1.domain.admin.AdminQuestion;
import com.andy.project1.util.BSResult;
import com.andy.project1.util.Constant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class AdminQuestionMgmtService {
    private final QuestionDao questionDao;
    private final CategoryDao categoryDao;
    private final ChoiceDao choiceDao;

    @Autowired
    public AdminQuestionMgmtService(QuestionDao questionDao, CategoryDao categoryDao, ChoiceDao choiceDao) {
        this.questionDao = questionDao;
        this.categoryDao = categoryDao;
        this.choiceDao = choiceDao;
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

    private boolean isParamStringEmpty(String s){
        return s == null || s.isEmpty();
    }
    @Transactional
    public BSResult addQuestion(Map<String, String> allParams) {
        Integer categoryId = allParams.get("category") == null ? null : Integer.valueOf(allParams.get("category"));
        Integer correctIdx = allParams.get("correctChoice") == null ? null
                : Integer.valueOf(allParams.get("correctChoice"));
        String description = allParams.get("questionDescription");
        if(categoryId == null)
            return new BSResult(false, "Please select one category");
        if(isParamStringEmpty(description))
            return new BSResult(false, "Please enter the question description");
        if(correctIdx == null)
            return new BSResult(false, "Please select one correct answer");
        // TODO: hard code here. (Need to refactor future. Not urgent)
        Choice[] choices = new Choice[Constant.ADMIN_ADD_QUESTION_NUMBER];
        int numOfChoices = 0;
        for(int i = 1; i <= Constant.ADMIN_ADD_QUESTION_NUMBER; i++){
            String s = allParams.get("choice" + i);
            if((isParamStringEmpty(s)) && correctIdx == i){
                return new BSResult(false, "Correct choice answer cannot be empty");
            }
            if(!isParamStringEmpty(s)){
                choices[i - 1] = new Choice();
                choices[i - 1].setIs_correct(correctIdx == i);
                choices[i - 1].setDescription(s);
                numOfChoices++;
            }
        }
        if(numOfChoices < 2){
            return new BSResult(false, "Please at least provide 2 choices");
        }
        Question question = new Question(null, categoryId, description, true);
        int newQuestionId = questionDao.addQuestionAndGetId(question);
        for(int i = 1; i < Constant.ADMIN_ADD_QUESTION_NUMBER; i++){
            if(choices[i-1] != null){
                choices[i - 1].setQuestion_id(newQuestionId);
                choiceDao.addChoice(choices[i - 1]);
            }
        }
        return new BSResult(true, "Success");
    }
}
