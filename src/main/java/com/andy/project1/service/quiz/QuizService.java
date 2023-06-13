package com.andy.project1.service.quiz;

import com.andy.project1.dao.*;
import com.andy.project1.domain.*;
import com.andy.project1.domain.util.QQAndChoicesDomain;
import com.andy.project1.domain.util.QuizPlus;
import com.andy.project1.domain.util.QuizQuestionDomain;
import com.andy.project1.util.BSResult;
import com.andy.project1.util.Constant;
import com.andy.project1.util.TimestampHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.*;
import java.util.stream.Collectors;

import static com.andy.project1.util.Constant.QUIZ_POOL_SIZE;

@Service
public class QuizService {
    private final QuizDao quizDao;

    private final QuizQuestionDao quizQuestionDao;
    private final QuestionDao questionDao;
    private final ChoiceDao choiceDao;
    private final CategoryDao categoryDao;
    @Autowired
    public QuizService(QuizDao quizDao, QuizQuestionDao quizQuestionDao, QuestionDao questionDao, ChoiceDao choiceDao, CategoryDao categoryDao) {
        this.quizDao = quizDao;
        this.quizQuestionDao = quizQuestionDao;
        this.questionDao = questionDao;
        this.choiceDao = choiceDao;
        this.categoryDao = categoryDao;
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
        // TODO: need to forbid create test if Question pool size < 5
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

    public QuizPlus finishQuiz(Map<String, String> params){
        // TODO: do valid check
        int categoryID = Integer.valueOf(params.get("categoryId"));
        int curIdx = Integer.valueOf(params.get("curIdx"));
        Category category = categoryDao.getCategoryById(categoryID);
        QuizPlus quizPlus = new QuizPlus(
                category, new LinkedList<>(), new LinkedList<>(), new LinkedList<>(),
                curIdx
        );
        for(int i = 0; i < QUIZ_POOL_SIZE; i++){
            quizPlus.getUserChoices().add(-1);
            int questionId = Integer.valueOf(params.get("questionId"+i));
            Question question = questionDao.getQuestionById(questionId);
            quizPlus.getQuestionList().add(question);
            List<Choice> choices = choiceDao.getChoicesByQuestionId(questionId);
            quizPlus.getChoices().add(choices);
            // cache old value
            if(params.get("userChoice"+i) != null){
                int selectChoiceID = Integer.valueOf(params.get("userChoice"+i));
                quizPlus.getUserChoices().set(i, selectChoiceID);
            }
            // get current selection
            if(params.get("choice"+i) != null){
                int selectChoiceID = Integer.valueOf(params.get("choice"+i));
                quizPlus.getUserChoices().set(i, selectChoiceID);
            }
        }
        String isNext = params.get("next");
        String isPrev = params.get("prev");
        if(isPrev != null){
            quizPlus.setCurIdx(Math.max(curIdx-1, 0));
        }
        if(isNext != null){
            quizPlus.setCurIdx(Math.min(curIdx+1, quizPlus.getUserChoices().size() - 1));
        }
        return quizPlus;
    }

    public QuizPlus getQuizPlus(int categoryId){
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
        Category category = categoryDao.getCategoryById(categoryId);
        List<Question> questions = new LinkedList<>();
        List<List<Choice>> choices = new LinkedList<>();
        for(Integer index : indexSet){
            Question question = questionPool.get(index);
            questions.add(question);
            List<Choice> tempChoices = choiceDao.getChoicesByQuestionId(question.getQuestion_id());
            choices.add(tempChoices);
        }
        List<Integer> tempUseChoice = new LinkedList<>();
        for(int i = 0; i < QUIZ_POOL_SIZE; i++)
            tempUseChoice.add(-1);
        return new QuizPlus(
                category, questions, choices, tempUseChoice, 0
        );
    }

    @Transactional
    public void submitQuizPlus(Map<String,String> allParams, QuizPlus quizPlus, Model model, User user){
        Category category = quizPlus.getCategory();
        Timestamp startTime = Timestamp.valueOf(allParams.get("startTime"));
        Quiz quiz = new Quiz(
                null,
                user.getUser_id(),
                category.getCategory_id(),
                category.getName()+"-Quiz-"+ TimestampHelper.timeStampToFormatData(startTime),
                startTime,
                Timestamp.from(Instant.now()));
        Integer quizId = quizDao.addQuizAndGetID(quiz);
        for(int i = 0; i < QUIZ_POOL_SIZE; i++){
            Question curQuestion = quizPlus.getQuestionList().get(i);
            Integer curChoice = quizPlus.getUserChoices().get(i);
            QuizQuestion quizQuestion = new QuizQuestion(
                    null,
                    quizId,
                    curQuestion.getQuestion_id(),
                    curChoice == -1 ? null : curChoice
            );
            addAQuizQuestion(quizQuestion);
        }
        model.addAttribute(Constant.ALERT_MSG, "Finished Quiz%20Finished!");
    }

}
