package com.andy.project1.service.quiz;

import com.andy.project1.dao.ChoiceDao;
import com.andy.project1.dao.QuestionDao;
import com.andy.project1.dao.QuizDao;
import com.andy.project1.dao.QuizQuestionDao;
import com.andy.project1.domain.Choice;
import com.andy.project1.domain.Question;
import com.andy.project1.domain.Quiz;
import com.andy.project1.domain.QuizQuestion;
import com.andy.project1.domain.util.QQAndChoicesDomain;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class QuizService {
    private final QuizDao quizDao;

    private final QuizQuestionDao quizQuestionDao;
    private final QuestionDao questionDao;
    private final ChoiceDao choiceDao;
    @Autowired
    public QuizService(QuizDao quizDao, QuizQuestionDao quizQuestionDao, QuestionDao questionDao, ChoiceDao choiceDao) {
        this.quizDao = quizDao;
        this.quizQuestionDao = quizQuestionDao;
        this.questionDao = questionDao;
        this.choiceDao = choiceDao;
    }

    public List<Choice> getChoiceByQuestionId(Integer id){
        return choiceDao.getChoicesByQuestionId(id);
    }
    public float getScores(Quiz quiz){
        List<QuizQuestion> quizQuestions = quizQuestionDao.getQuizQuestionByQuizId(quiz.getQuiz_id());
        int len = quizQuestions.size();
        int correctNum = 0;
        for(QuizQuestion qq : quizQuestions){
            // for each quiz question, we need to get question first
            Question question = questionDao.getQuestionById(qq.getQuestion_id());
            // for each question, we get all possible choice
            List<Choice> choices = choiceDao.getChoicesByQuestionId(question.getQuestion_id());
            boolean isCorrect = false;
            // We check if our choice is correct
            for(Choice choice : choices){
                if(choice.getChoice_id() == qq.getUser_choice_id() && choice.getIs_correct()){
                    isCorrect = true;
                    break;
                }
            }
            if(isCorrect) correctNum ++;
        }
        return correctNum * 100.0f / len;
    }

    public List<Quiz> getQuizHistory(Integer id){
        List<Quiz> quiz = quizDao.getQuizByUserId(id);
        return quiz.stream().filter(q -> q.getTime_end() != null).collect(Collectors.toList());
    }

    public Quiz getOngoing(Integer id){
        List<Quiz> quiz = quizDao.getQuizByUserId(id);
        Quiz ongoingQuiz = null;
        for(Quiz q : quiz){
            if(q.getTime_end() == null){
                ongoingQuiz = q;
                break;
            }
        }
        return ongoingQuiz;
    }
}
