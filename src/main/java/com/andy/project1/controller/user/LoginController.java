package com.andy.project1.controller.user;

import com.andy.project1.domain.User;
import com.andy.project1.service.user.LoginService;
import com.andy.project1.util.Constant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
public class LoginController {
    private final LoginService loginService;

    @Autowired
    public LoginController(LoginService loginService) {
        this.loginService = loginService;
    }

    @GetMapping("/login")
    public String getLogin(HttpServletRequest request, Model model){
        HttpSession session = request.getSession(false);
        if(session != null && session.getAttribute(Constant.LOGIN_SESSION_KEY) != null){
            // Give logged in info
            User user = (User) session.getAttribute(Constant.LOGIN_SESSION_KEY);
            model.addAttribute(Constant.IS_LOGIN, true);
            model.addAttribute(Constant.IS_ADMIN, user.getIs_admin());
            return "redirect:/quiz";
        }
        return "login";
    }

    @PostMapping("/login")
    public String postLogin(@RequestParam String email,
                            @RequestParam String password,
                            HttpServletRequest request,
                            Model model){
        User user = loginService.login(email, password, model);
        if(user == null){
            return "/login";
        }else{
            HttpSession oldSession = request.getSession(false);
            if (oldSession != null) oldSession.invalidate();
            HttpSession newSession = request.getSession(true);
            newSession.setAttribute(Constant.LOGIN_SESSION_KEY, user);
            model.addAttribute(Constant.IS_LOGIN, true);
            model.addAttribute(Constant.IS_ADMIN, user.getIs_admin());
            return "redirect:/quiz";
        }
    }

    @GetMapping("/logout")
    public String logout(HttpServletRequest request, Model model){
        HttpSession oldSession = request.getSession(false);
        if(oldSession != null) oldSession.invalidate();
        model.addAttribute(Constant.IS_LOGIN, false);
        return "login";
    }
}
