package com.andy.project1.controller.quiz;

import com.andy.project1.domain.User;
import com.andy.project1.domain.util.LoggedUser;
import com.andy.project1.domain.util.QuizQuestionDomain;
import com.andy.project1.service.quiz.QuizService;
import com.andy.project1.util.Constant;
import com.andy.project1.util.HttpSessionHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;

@Controller
public class QuizResultController {
    private final QuizService quizService;

    @Autowired
    public QuizResultController(QuizService quizService) {
        this.quizService = quizService;
    }
    @GetMapping("/quizresult")
    public String getQuizResult(@RequestParam("quiz_id") Integer quizId, HttpServletRequest request, Model model){
        User user = HttpSessionHelper.getSessionUser(request);
        model.addAttribute(Constant.IS_LOGIN, true);
        model.addAttribute(Constant.IS_ADMIN, user.getIs_admin());
        // used for user info
        LoggedUser loggedUser = new LoggedUser(user);
        // get all choice and your choice
        QuizQuestionDomain quizResult = quizService.getQuizResult(quizId);
        // convert to time
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String formattedTimestamp = sdf.format(quizResult.getQuiz().getTime_end());
        // get score
        int scores = quizService.getScores(quizResult.getQuiz());
        model.addAttribute(Constant.QUIZ_RES, quizResult);
        model.addAttribute(Constant.LOGGED_USER, loggedUser);
        model.addAttribute(Constant.QUIZ_RES_END_TIME, formattedTimestamp);
        model.addAttribute(Constant.QUIZ_RES_SCORE, scores);
        return "quizresult";
    }
}
