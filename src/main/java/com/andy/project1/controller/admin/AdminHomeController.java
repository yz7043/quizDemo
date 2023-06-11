package com.andy.project1.controller.admin;

import com.andy.project1.domain.User;
import com.andy.project1.util.Constant;
import com.andy.project1.util.HttpSessionHelper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
public class AdminHomeController {
    @GetMapping("/adminhome")
    public String getAdminHome(HttpServletRequest request, Model model){
        User user = HttpSessionHelper.getSessionUser(request);
        model.addAttribute(Constant.IS_LOGIN, true);
        model.addAttribute(Constant.IS_ADMIN, user.getIs_admin());
        if(!user.getIs_admin())
            return "redirect:/home";
        return "adminHome";
    }
}
