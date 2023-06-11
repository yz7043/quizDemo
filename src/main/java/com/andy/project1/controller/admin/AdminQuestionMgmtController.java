package com.andy.project1.controller.admin;


import com.andy.project1.domain.Category;
import com.andy.project1.domain.Question;
import com.andy.project1.domain.User;
import com.andy.project1.domain.admin.AdminQuestion;
import com.andy.project1.service.admin.AdminQuestionMgmtService;
import com.andy.project1.service.category.CategoryService;
import com.andy.project1.util.BSResult;
import com.andy.project1.util.Constant;
import com.andy.project1.util.HttpSessionHelper;
import org.apache.tomcat.util.bcel.Const;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.HttpRequestHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.jws.WebParam;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;

@Controller
public class AdminQuestionMgmtController {
    private final AdminQuestionMgmtService adminQuestionMgmtService;
    private final CategoryService categoryService;
    @Autowired
    public AdminQuestionMgmtController(AdminQuestionMgmtService adminQuestionMgmtService, CategoryService categoryService) {
        this.adminQuestionMgmtService = adminQuestionMgmtService;
        this.categoryService = categoryService;
    }

    @GetMapping("/adminQuestionMgmt")
    public String getAdminQuestionMgmt(HttpServletRequest request, Model model){
        User user = HttpSessionHelper.getSessionUser(request);
        if(user == null || !user.getIs_admin()){
            return "redirect:/home";
        }
        HttpSessionHelper.addUserInfoToModel(user, model);
        getAllQuestions(model);
        return "adminQuestionMgmt";
    }

    @GetMapping("/adminQuestionMgmtToggle")
    public String getAdminQuetionMgmtToggle(@RequestParam("question_id") int id,
                                            HttpServletRequest request, Model model){
        User user = HttpSessionHelper.getSessionUser(request);
        if(user == null || !user.getIs_admin()){
            return "redirect:/home";
        }
        HttpSessionHelper.addUserInfoToModel(user, model);
        BSResult bsResult = adminQuestionMgmtService.toggleAQuestion(id);
        if(!bsResult.getSuccess()){
            model.addAttribute(Constant.ALERT_MSG, bsResult.getMsg());
            getAllQuestions(model);
            return "adminQuestionMgmt";
        }
        getAllQuestions(model);
        return "redirect:/adminQuestionMgmt";
    }

    private void getAllQuestions(Model model){
        List<AdminQuestion> allQuestions = adminQuestionMgmtService.getQuestionWithCategory();
        List<Category> allCategory = categoryService.getAllCategory();
        model.addAttribute(Constant.ADMIN_QUESTION_MGMT_QUESTIONS, allQuestions);
        model.addAttribute(Constant.ADMIN_QUESTION_MGMT_CATEGORIES, allCategory);
    }
}
