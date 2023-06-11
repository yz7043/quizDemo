package com.andy.project1.util;

import com.andy.project1.domain.User;
import com.andy.project1.domain.util.LoggedUser;
import org.springframework.ui.Model;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class HttpSessionHelper {
    public static User getSessionUser(HttpServletRequest request){
        HttpSession session = request.getSession(false);
        User user = null;
        if(session != null && session.getAttribute(Constant.LOGIN_SESSION_KEY) != null){
            user = (User) session.getAttribute(Constant.LOGIN_SESSION_KEY);
        }
        return user;
    }

    public static void addUserInfoToModel(User user, Model model){
        model.addAttribute(Constant.IS_LOGIN, true);
        model.addAttribute(Constant.IS_ADMIN, user.getIs_admin());
        LoggedUser loggedUser = new LoggedUser(user);
        model.addAttribute(Constant.LOGGED_USER, loggedUser);
    }
}
