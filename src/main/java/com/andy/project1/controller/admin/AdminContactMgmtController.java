package com.andy.project1.controller.admin;

import com.andy.project1.domain.Contact;
import com.andy.project1.domain.User;
import com.andy.project1.service.admin.AdminContactMgmtService;
import com.andy.project1.util.Constant;
import com.andy.project1.util.HttpSessionHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class AdminContactMgmtController {
    private final AdminContactMgmtService adminContactMgmtService;

    @Autowired
    public AdminContactMgmtController(AdminContactMgmtService adminContactMgmtService) {
        this.adminContactMgmtService = adminContactMgmtService;
    }

    @GetMapping("/adminContactManagement")
    public String getAdminContactMgmt(HttpServletRequest request, Model model){
        User user = HttpSessionHelper.getSessionUser(request);
        if(user != null){
            HttpSessionHelper.addUserInfoToModel(user, model);
        }
        if(!user.getIs_admin() || !user.getIs_active()){
            return "redirect:/home";
        }
        List<Contact> allContacts = adminContactMgmtService.getAllContacts();
        model.addAttribute(Constant.ADMIN_CONTACT_LIST, allContacts);
        return "adminContactManagement";
    }
}
