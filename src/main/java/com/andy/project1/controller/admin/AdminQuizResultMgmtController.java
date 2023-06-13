package com.andy.project1.controller.admin;

import com.andy.project1.domain.User;
import com.andy.project1.domain.admin.AdminQuizResult;
import com.andy.project1.domain.util.QuizQuestionDomain;
import com.andy.project1.service.admin.AdminQuizResultMgmtService;
import com.andy.project1.service.quiz.QuizService;
import com.andy.project1.util.Constant;
import com.andy.project1.util.HttpSessionHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.relational.core.sql.In;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

@Controller
public class AdminQuizResultMgmtController {
    private final AdminQuizResultMgmtService adminQuizResultMgmtService;
    private final QuizService quizService;

    @Autowired
    public AdminQuizResultMgmtController(AdminQuizResultMgmtService adminQuizResultMgmtService, QuizService quizService) {
        this.adminQuizResultMgmtService = adminQuizResultMgmtService;
        this.quizService = quizService;
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
        if(params.get("category") != null){
            Collections.sort(adminQuizResult, Comparator.comparing(aq -> aq.getCategory().getName()));
            model.addAttribute(Constant.ADMIN_QUIZ_RESULT_SORT_CATEGORY, true);
        }
        if(params.get("fullname") != null){
            Collections.sort(adminQuizResult,
                    Comparator.comparing(aq -> aq.getUser().getFirstname() + " " + aq.getUser().getLastname()));
            model.addAttribute(Constant.ADMIN_QUIZ_RESULT_SORT_FULLNAME, true);
        }
        model.addAttribute(Constant.ADMIN_QUIZ_RESULT, adminQuizResult);
        return "adminQuizResultMgmt";
    }

    @GetMapping("/adminQuizResultDetail")
    public String getAdminQuizResultDetail(@RequestParam("quizId") Integer quizId,
                                           HttpServletRequest request, Model model){
        User user = HttpSessionHelper.getSessionUser(request);
        if(user == null || !user.getIs_admin()){
            return "redirect:/home";
        }
        HttpSessionHelper.addUserInfoToModel(user, model);
        // get all choice and your choice
        QuizQuestionDomain quizResult = quizService.getQuizResult(quizId);
        // convert to time
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String formattedTimestamp = sdf.format(quizResult.getQuiz().getTime_end());
        // get score
        int scores = quizService.getScores(quizResult.getQuiz());
        model.addAttribute(Constant.QUIZ_RES, quizResult);
        model.addAttribute(Constant.QUIZ_RES_END_TIME, formattedTimestamp);
        model.addAttribute(Constant.QUIZ_RES_SCORE, scores);
        return "quizresult";
    }

}
