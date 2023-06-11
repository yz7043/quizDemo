package com.andy.project1.controller.quiz;

import com.andy.project1.domain.Question;
import com.andy.project1.domain.Quiz;
import com.andy.project1.domain.QuizQuestion;
import com.andy.project1.domain.User;
import com.andy.project1.domain.util.LoggedUser;
import com.andy.project1.domain.util.QuizQuestionDomain;
import com.andy.project1.service.category.CategoryService;
import com.andy.project1.service.quiz.QuizService;
import com.andy.project1.util.Constant;
import com.andy.project1.util.HttpSessionHelper;
import com.andy.project1.util.TimestampHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.sql.Timestamp;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Map;

@Controller
public class QuizController {
    private final QuizService quizService;
    private final CategoryService categoryService;
    @Autowired
    public QuizController(QuizService quizService, CategoryService categoryService) {
        this.quizService = quizService;
        this.categoryService = categoryService;
    }

    @GetMapping("/quiz")
    public String getQuiz(@RequestParam("category_id") Integer categoryId, HttpServletRequest request, Model model) {
        User user = HttpSessionHelper.getSessionUser(request);
        model.addAttribute(Constant.IS_LOGIN, true);
        model.addAttribute(Constant.IS_ADMIN, user.getIs_admin());
        LoggedUser loggedUser = new LoggedUser(user);
        model.addAttribute(Constant.LOGGED_USER, user);
        // create quiz question by service
        QuizQuestionDomain quizQuestionDomain = quizService.createQuiz(categoryId);
        model.addAttribute(Constant.QUIZ_QUESTION, quizQuestionDomain);
        model.addAttribute(Constant.QUIZ_CATEGORY, categoryService.getCategoryById(categoryId));
        Timestamp truncateTimestamp = Timestamp.from(Instant.now().truncatedTo(ChronoUnit.SECONDS));
        model.addAttribute(Constant.QUIZ_START_TIME, truncateTimestamp);
        return "quiz";
    }

    @PostMapping("/quiz")
    @Transactional
    public String submitQuiz(@RequestParam Map<String,String> allParams, HttpServletRequest request, Model model){
        // TODO: shit mountain need to be re-factor (how to determine if login in jsp)
        User user = HttpSessionHelper.getSessionUser(request);
        model.addAttribute(Constant.IS_LOGIN, true);
        model.addAttribute(Constant.IS_ADMIN, user.getIs_admin());
        LoggedUser loggedUser = new LoggedUser(user);
        model.addAttribute(Constant.LOGGED_USER, user);

        Integer categoryId = Integer.valueOf(allParams.get("categoryId"));
        String categoryName = allParams.get("categoryName");
        Timestamp startTime = Timestamp.valueOf(allParams.get("startTime"));
        Quiz quiz = new Quiz(
                null,
                user.getUser_id(),
                categoryId,
                categoryName+"-Quiz-"+ TimestampHelper.timeStampToFormatData(startTime),
                startTime,
                Timestamp.from(Instant.now()));
        // create the quiz
        Integer quizId = quizService.addAQuiz(quiz);
        for(int i = 0; i < Constant.QUIZ_POOL_SIZE; i++){
            // get the question idx & id
            String questionIdx = Constant.QUIZ_SUBMIT_QUESTION_PREFIX+i;
            Integer questionId = allParams.get(questionIdx) == null ? null : Integer.valueOf(allParams.get(questionIdx));
            // get choice idx(based on question id) & choice id
            String choiceIdx = Constant.QUIZ_SUBMIT_CHOICE_PREFIX+(questionId);
            Integer choiceId = allParams.get(choiceIdx) == null ? null : Integer.valueOf(allParams.get(choiceIdx));
            QuizQuestion quizQuestion = new QuizQuestion(
                    null,
                    quizId,
                    questionId,
                    choiceId
            );
            // add quiz to db
            quizService.addAQuizQuestion(quizQuestion);
        }
        return "redirect:/home";
    }
}
