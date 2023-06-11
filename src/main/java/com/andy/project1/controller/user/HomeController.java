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

import javax.servlet.http.HttpServletRequest;
import java.util.LinkedList;
import java.util.List;
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
    public String getHome(HttpServletRequest request, Model model) {
        User user = HttpSessionHelper.getSessionUser(request);
        model.addAttribute(Constant.IS_LOGIN, true);
        model.addAttribute(Constant.IS_ADMIN, user.getIs_admin());

        if (!user.getIs_admin()){
            // 1. if it isn't admin
                // 1.1 get all category
            List<Category> categories =  categoryService.getAllCategory();
            model.addAttribute(Constant.ALL_CATEGORIES, categories);
                // 1.2 get if there is ongoing test
            Quiz ongointQuiz = quizService.getOngoing(user.getUser_id());
            model.addAttribute(Constant.ONGOING_QUIZ, ongointQuiz);
                // 1.3 get test result
            List<Quiz> historyQuiz = quizService.getQuizHistory(user.getUser_id());
            List<Integer> historyScore = historyQuiz.stream().map(quiz -> quizService.getScores(quiz)).collect(Collectors.toList());
            List<QuizScore> quizScores = new LinkedList<>();
            for(int i = 0; i < historyQuiz.size(); i++){
                quizScores.add(new QuizScore(historyQuiz.get(i), historyScore.get(i)));
            }
            model.addAttribute(Constant.HISTORY_QUIZ, quizScores);
        } else {
            // 2. if it is admin
        }
        return "/home";
    }
}
