package com.andy.project1.controller.quiz;

import com.andy.project1.domain.User;
import com.andy.project1.util.Constant;
import com.andy.project1.util.HttpSessionHelper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
public class QuizController {
    @GetMapping("/quiz")
    public String getQuiz(HttpServletRequest request, Model model) {
        User user = HttpSessionHelper.getSessionUser(request);
        model.addAttribute(Constant.IS_LOGIN, true);
        model.addAttribute(Constant.IS_ADMIN, user.getIs_admin());
        return "quiz";
    }
}
