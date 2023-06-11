package com.andy.project1.controller.contact;

import com.andy.project1.domain.Contact;
import com.andy.project1.domain.User;
import com.andy.project1.domain.util.LoggedUser;
import com.andy.project1.service.contact.ContactService;
import com.andy.project1.util.Constant;
import com.andy.project1.util.HttpSessionHelper;
import com.andy.project1.util.InputHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.relational.core.sql.In;
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
            HttpSessionHelper.addUserInfoToModel(user, model);
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
            HttpSessionHelper.addUserInfoToModel(user, model);
        }
        if(InputHelper.isEmpty(email)){
            model.addAttribute(Constant.ALERT_MSG, "Email is mpty");
            return "contact";
        }
        if(InputHelper.isEmpty(subject)){
            model.addAttribute(Constant.ALERT_MSG, "Subject is empty");
            return "contact";
        }
        if(InputHelper.isEmpty(message)){
            model.addAttribute(Constant.ALERT_MSG, "Message is empty");
            return "contact";
        }
        if(email.length() > Contact.EMAIL_MAX_LEN){
            model.addAttribute(Constant.ALERT_MSG, "Email to long" + Contact.EMAIL_MAX_LEN +
                    " Characters.");
            return "contact";
        }
        if(subject.length() > Contact.SUBJECT_MAX_LEN ){
            model.addAttribute(Constant.ALERT_MSG, "Subject to long" + Contact.SUBJECT_MAX_LEN +
                    " Characters.");
            return "contact";
        }
        if(message.length() > Contact.MSG_MAX_LEN ){
            model.addAttribute(Constant.ALERT_MSG, "Message to long. No more than " + Contact.MSG_MAX_LEN +
                    " Characters.");
            return "contact";
        }


        Contact contact = new Contact();
        contact.setMessage(message);
        contact.setEmail(email);
        contact.setSubject(subject);
        contact.setTime(Timestamp.from(Instant.now()));
        System.out.println("msg: " + message.length());
        System.out.println("email: " + email.length());
        System.out.println("subject: " + subject.length());
        boolean success = contactService.addContact(contact);
        if(success)
            model.addAttribute(Constant.ALERT_MSG, "Your message has been received!");
        else
            model.addAttribute(Constant.ALERT_MSG, "Submission fails, please try again later.");
        return "contact";
    }
}
