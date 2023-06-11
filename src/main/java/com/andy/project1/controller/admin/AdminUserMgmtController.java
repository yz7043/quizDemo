package com.andy.project1.controller.admin;

import com.andy.project1.domain.User;
import com.andy.project1.domain.admin.AdminUser;
import com.andy.project1.service.admin.AdminUserMgmtService;
import com.andy.project1.util.BSResult;
import com.andy.project1.util.Constant;
import com.andy.project1.util.HttpSessionHelper;
import com.sun.org.apache.bcel.internal.generic.IUSHR;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class AdminUserMgmtController {

    private final AdminUserMgmtService adminUserMgmtService;
    @Autowired
    public AdminUserMgmtController(AdminUserMgmtService adminUserMgmtService) {
        this.adminUserMgmtService = adminUserMgmtService;
    }

    @GetMapping("/adminUserMgmt")
    public String getAdminUserMgmt(HttpServletRequest request, Model model){
        User user = HttpSessionHelper.getSessionUser(request);
        if(user != null){
            HttpSessionHelper.addUserInfoToModel(user, model);
        }
        if(!user.getIs_admin() || !user.getIs_active()){
            model.addAttribute(Constant.ALERT_MSG, "You are not admin");
            return "redirect:/home";
        }
        List<AdminUser> allUserForDisplay = adminUserMgmtService.getAllUserForDisplay();
        model.addAttribute(Constant.ADMIN_USER_LIST, allUserForDisplay);
        return "adminUserMgmt";
    }

    @GetMapping("/adminUserToggleStatus")
    public String toggleUserStatus(@RequestParam("user_id") Integer userId, HttpServletRequest request, Model model){
        User user = HttpSessionHelper.getSessionUser(request);
        if(user != null){
            HttpSessionHelper.addUserInfoToModel(user, model);
        }
        if(!user.getIs_admin() || !user.getIs_active()){
            model.addAttribute(Constant.ALERT_MSG, "You are not admin");
            return "redirect:/home";
        }
        BSResult bsResult = adminUserMgmtService.toggleStatus(userId);
        if(!bsResult.getSuccess()){
            model.addAttribute(Constant.ALERT_MSG, bsResult.getMsg());
        }
        List<AdminUser> allUserForDisplay = adminUserMgmtService.getAllUserForDisplay();
        model.addAttribute(Constant.ADMIN_USER_LIST, allUserForDisplay);
        return "adminUserMgmt";
    }
}
