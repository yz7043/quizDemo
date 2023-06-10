package com.andy.project1.service.user;

import com.andy.project1.dao.UserDao;
import com.andy.project1.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import javax.servlet.http.HttpServletRequest;

@Service
public class LoginService {
    private final UserDao userDao;

    @Autowired
    public LoginService(UserDao userDao) {
        this.userDao = userDao;
    }

    private boolean isEnterTextEmpty(String s){
        return s == null || s.isEmpty();
    }
    public User login(String email, String password, Model model){
        if(isEnterTextEmpty(email)){
            model.addAttribute("error", "Email is empty");
            return null;
        }
        if(isEnterTextEmpty(password)){
            model.addAttribute("error", "Username is empty");
            return null;
        }
        User found = userDao.getUserByEmail(email);
        if(found == null || !found.getPassword().equals(password)){
            model.addAttribute("error", "Credential wrong");
            return null;
        }
        return found;
    }
}
