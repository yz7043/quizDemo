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
import com.andy.project1.domain.util.QuizQuestionDomain;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.relational.core.sql.In;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

import static com.andy.project1.util.Constant.QUIZ_POOL_SIZE;

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
    public int getScores(Quiz quiz){
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
        return correctNum;
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

    public QuizQuestionDomain getQuizResult(Integer quizId){
        Quiz curQuiz = quizDao.getQuizById(quizId);
        List<QuizQuestion> quizQuestions = quizQuestionDao.getQuizQuestionByQuizId(quizId);
        List<QQAndChoicesDomain> qqAndChoicesDomains = new ArrayList<>();
        for(QuizQuestion quizQuestion : quizQuestions){
            List<Choice> choices = choiceDao.getChoicesByQuestionId(quizQuestion.getQuestion_id());
            Question question = questionDao.getQuestionById(quizQuestion.getQuestion_id());
            qqAndChoicesDomains.add(
                    new QQAndChoicesDomain(quizQuestion, choices, question)
            );
        }
        return new QuizQuestionDomain(curQuiz, qqAndChoicesDomains);
    }

    public QuizQuestionDomain createQuiz(Integer categoryId){
        // Select random Question
        List<Question> questionPool = questionDao.getQuestionsByCategory(categoryId);
        Set<Integer> indexSet = new HashSet<>();
        Random randomGen = new Random();
        for(int i = 0; i < QUIZ_POOL_SIZE; i++){
            int nextRandom = randomGen.nextInt(questionPool.size());
            while (indexSet.contains(nextRandom) || !questionPool.get(nextRandom).getIs_active()){
                nextRandom = randomGen.nextInt(questionPool.size());
            }
            indexSet.add(nextRandom);
        }
        // Create a QuizQuesitonDomain for return
        // We first don't support retaken ongoing quiz
        QuizQuestionDomain result = new QuizQuestionDomain();
        result.setQuestions(new LinkedList<>());
        for(Integer index : indexSet){
            Question question = questionPool.get(index);
            List<Choice> choices = choiceDao.getChoicesByQuestionId(question.getQuestion_id());
            List<QQAndChoicesDomain> qqQuestions = result.getQuestions();
            qqQuestions.add(new QQAndChoicesDomain(null, choices, question));
            qqQuestions.get(qqQuestions.size() - 1).removeCorrectChoice();
        }
        return result;
    }

    public Integer addAQuiz(Quiz q){
        return quizDao.addQuizAndGetID(q);
    }

    public boolean addAQuizQuestion(QuizQuestion quizQuestion){
        int result = quizQuestionDao.addQuizQuestion(quizQuestion);
        return result > 0;
    }
}
