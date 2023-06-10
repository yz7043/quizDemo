package com.andy.project1.controller.user;

import com.andy.project1.service.user.RegisterService;
import com.andy.project1.util.BSResult;
import com.andy.project1.util.Constant;
import javafx.util.Pair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
public class RegisterController {

    private final RegisterService registerService;
    @Autowired
    public RegisterController(RegisterService registerService) {
        this.registerService = registerService;
    }

    @GetMapping("/register")
    public String getRegister(HttpServletRequest request, Model model){
        HttpSession session = request.getSession(false);
        if(session != null && session.getAttribute(Constant.LOGIN_SESSION_KEY) != null){
            return "redirect:/quiz";
        }
        return "register";
    }

    @PostMapping("/register")
    public String postRegister(HttpServletRequest request, Model model){
        BSResult result = registerService.register(request);
        if(result.getSuccess()){
            // success
            model.addAttribute("alertMsg", "Register Successful");
            return "/register";
        }else {
            // fail
            model.addAttribute("error", result.getMsg());
            return "/register";
        }
    }
}
