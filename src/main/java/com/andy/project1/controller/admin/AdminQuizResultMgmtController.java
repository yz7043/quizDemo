package com.andy.project1.controller.admin;

import com.andy.project1.domain.Quiz;
import com.andy.project1.domain.User;
import com.andy.project1.domain.admin.AdminQuizResult;
import com.andy.project1.service.admin.AdminQuizResultMgmtService;
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
import java.util.List;
import java.util.Map;

@Controller
public class AdminQuizResultMgmtController {
    private final AdminQuizResultMgmtService adminQuizResultMgmtService;

    @Autowired
    public AdminQuizResultMgmtController(AdminQuizResultMgmtService adminQuizResultMgmtService) {
        this.adminQuizResultMgmtService = adminQuizResultMgmtService;
    }

    @GetMapping("/adminQuizResultMgmt")
    public String getAdminQuizResult(@RequestParam Map<String, String> params,
                                     HttpServletRequest request, Model model){
        User user = HttpSessionHelper.getSessionUser(request);
        if(user == null || !user.getIs_admin()){
            return "redirect:/home";
        }
        HttpSessionHelper.addUserInfoToModel(user, model);


        List<AdminQuizResult> adminQuizResult = adminQuizResultMgmtService.getAdminQuizResult(params, request, model);
        model.addAttribute(Constant.ADMIN_QUIZ_RESULT, adminQuizResult);
        return "adminQuizResultMgmt";
    }

}
