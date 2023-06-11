package com.andy.project1.controller.contact;

import com.andy.project1.domain.Contact;
import com.andy.project1.domain.User;
import com.andy.project1.domain.util.LoggedUser;
import com.andy.project1.service.contact.ContactService;
import com.andy.project1.util.Constant;
import com.andy.project1.util.HttpSessionHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.sql.Timestamp;
import java.time.Instant;

@Controller
public class ContactController {

    private final ContactService contactService;

    @Autowired
    public ContactController(ContactService contactService) {
        this.contactService = contactService;
    }

    @GetMapping("/contactus")
    public String getContactUs(HttpServletRequest request, Model model){
        User user = HttpSessionHelper.getSessionUser(request);
        if(user != null){
            LoggedUser loggedUser = new LoggedUser(user);
            model.addAttribute(Constant.LOGGED_USER, loggedUser);
        }
        return "contact";
    }

    @PostMapping("/contactus")
    public String postContactUs(
            @RequestParam String email,
            @RequestParam String subject,
            @RequestParam String message,
            HttpServletRequest request,
            Model model
    ) {
        User user = HttpSessionHelper.getSessionUser(request);
        if(user != null){
            LoggedUser loggedUser = new LoggedUser(user);
            model.addAttribute(Constant.LOGGED_USER, loggedUser);
        }
        Contact constant = new Contact(null, email, subject, message, Timestamp.from(Instant.now()));
        boolean success = contactService.addContact(constant);
        if(success)
            model.addAttribute(Constant.ALERT_MSG, "Your message has been received!");
        else
            model.addAttribute(Constant.ALERT_MSG, "Submission fails, please try again later.");
        return "contact";
    }
}
