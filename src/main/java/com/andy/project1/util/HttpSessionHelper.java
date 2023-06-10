package com.andy.project1.util;

import com.andy.project1.domain.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class HttpSessionHelper {
    public static User getSessionUser(HttpServletRequest request){
        HttpSession session = request.getSession(false);
        User user = (User) session.getAttribute(Constant.LOGIN_SESSION_KEY);
        return user;
    }
}
