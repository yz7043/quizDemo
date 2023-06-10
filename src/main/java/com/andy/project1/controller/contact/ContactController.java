package com.andy.project1.controller.contact;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
public class ContactController {
    @GetMapping("/contactus")
    public String getContactUs(Model model){
        return "contact";
    }
}
