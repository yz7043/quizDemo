package com.andy.project1.controller.user;


import com.andy.project1.domain.Category;
import com.andy.project1.domain.Quiz;
import com.andy.project1.domain.User;
import com.andy.project1.domain.util.QuizScore;
import com.andy.project1.service.category.CategoryService;
import com.andy.project1.service.quiz.QuizService;
import com.andy.project1.util.Constant;
import com.andy.project1.util.HttpSessionHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.sql.Timestamp;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Controller
public class HomeController {
    private final QuizService quizService;
    private final CategoryService categoryService;

    @Autowired
    public HomeController(QuizService quizService, CategoryService categoryService) {
        this.quizService = quizService;
        this.categoryService = categoryService;
    }

    @GetMapping("/home")
    public String getHome(@RequestParam Map<String, String> params, HttpServletRequest request, Model model) {
        User user = HttpSessionHelper.getSessionUser(request);
        model.addAttribute(Constant.IS_LOGIN, true);
        model.addAttribute(Constant.IS_ADMIN, user.getIs_admin());

        if (!user.getIs_admin()){
            // 1. if it isn't admin
                // 1.1 get all category
            List<Category> categories =  categoryService.getAllValidCategory();
            model.addAttribute(Constant.ALL_CATEGORIES, categories);
                // 1.2 get if there is ongoing test
            Quiz ongointQuiz = quizService.getOngoing(user.getUser_id());
            model.addAttribute(Constant.ONGOING_QUIZ, ongointQuiz);
                // 1.3 get test result
            List<Quiz> historyQuiz = quizService.getQuizHistory(user.getUser_id());
            Collections.sort(historyQuiz, (q1, q2) -> {
                Timestamp t1 = q1.getTime_start();
                Timestamp t2 = q2.getTime_start();
                return t2.compareTo(t1);
            });
            List<Integer> historyScore = historyQuiz.stream().map(quiz -> quizService.getScores(quiz)).collect(Collectors.toList());
            List<QuizScore> quizScores = new LinkedList<>();
            for(int i = 0; i < historyQuiz.size(); i++){
                quizScores.add(new QuizScore(historyQuiz.get(i), historyScore.get(i)));
            }
            model.addAttribute(Constant.HISTORY_QUIZ, quizScores);
        } else {
            // 2. if it is admin
            return "redirect:/adminhome";
        }
        if(params.get(Constant.ALERT_MSG) != null){
            model.addAttribute(Constant.ALERT_MSG, params.get(Constant.ALERT_MSG));
        }
        return "/home";
    }
}
