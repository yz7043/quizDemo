package com.andy.project1.service.user;

import com.andy.project1.dao.UserDao;
import com.andy.project1.domain.User;
import com.andy.project1.util.BSResult;
import javafx.util.Pair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;

@Service
public class RegisterService {
    private final UserDao userDao;

    @Autowired
    public RegisterService(UserDao userDao) {
        this.userDao = userDao;
    }
    private boolean isEnterTextEmpty(String s){
        return s == null || s.isEmpty();
    }
    public BSResult register(HttpServletRequest request){
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String rePassword = request.getParameter("passwordConfirm");
        String firstname = request.getParameter("firstname");
        String lastname = request.getParameter("lastname");
        if(isEnterTextEmpty(email))
            return new BSResult(false, "Email is empty");
        if(isEnterTextEmpty(password))
            return new BSResult(false, "Password is empty");
        if(isEnterTextEmpty(rePassword))
            return new BSResult(false, "Confirm password is empty");
        if(isEnterTextEmpty(firstname))
            return new BSResult(false, "Firstname is empty");
        if(isEnterTextEmpty(lastname))
            return new BSResult(false, "Lastname is empty");
        if(!password.equals(rePassword))
            return new BSResult(false, "Two passwords don't match");
        User found = userDao.getUserByEmail(email);
        if(found != null)
            return new BSResult(false, "Email has been used");
        User user = new User(
                null, email, password, firstname, lastname, true, false
        );

        int addResult = userDao.addUser(user);
        if(addResult == 1){
            return new BSResult(true, "Register success");
        }else{
            return new BSResult(false, "Database Error");
        }
    }
}
