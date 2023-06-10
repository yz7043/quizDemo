package com.andy.project1.controller.quiz;

import com.andy.project1.domain.User;
import com.andy.project1.util.Constant;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
public class QuizController {
    private User getSessionUser(HttpServletRequest request){
        HttpSession session = request.getSession(false);
        User user = (User) session.getAttribute(Constant.LOGIN_SESSION_KEY);
        return user;
    }
    @GetMapping("/quiz")
    public String getQuiz(HttpServletRequest request, Model model) {
        User user = getSessionUser(request);
        model.addAttribute(Constant.IS_LOGIN, true);
        model.addAttribute(Constant.IS_ADMIN, user.getIs_admin());
        return "quiz";
    }
}
