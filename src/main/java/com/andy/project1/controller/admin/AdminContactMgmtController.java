package com.andy.project1.controller.admin;

import com.andy.project1.domain.Contact;
import com.andy.project1.domain.User;
import com.andy.project1.service.admin.AdminContactMgmtService;
import com.andy.project1.service.contact.ContactService;
import com.andy.project1.util.Constant;
import com.andy.project1.util.HttpSessionHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class AdminContactMgmtController {
    private final AdminContactMgmtService adminContactMgmtService;
    private final ContactService contactService;

    @Autowired
    public AdminContactMgmtController(AdminContactMgmtService adminContactMgmtService, ContactService contactService) {
        this.adminContactMgmtService = adminContactMgmtService;
        this.contactService = contactService;
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

    @GetMapping("/contactDetails")
    public String showContacDetail(@RequestParam("contact_id") int contactId,
                                   HttpServletRequest request, Model model){
        User user = HttpSessionHelper.getSessionUser(request);
        if(user != null){
            HttpSessionHelper.addUserInfoToModel(user, model);
        }
        if(!user.getIs_admin() || !user.getIs_active()){
            return "redirect:/home";
        }
        Contact contact = contactService.getContactById(contactId);
        if(contact == null){
            return "redirect:/adminHome";
        }else{
            model.addAttribute(Constant.ADMIN_CONTACT_DETAIL, contact);
            return "contactDetails";
        }
    }
}
