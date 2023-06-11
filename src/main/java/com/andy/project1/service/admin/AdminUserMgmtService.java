package com.andy.project1.service.admin;

import com.andy.project1.dao.UserDao;
import com.andy.project1.domain.User;
import com.andy.project1.domain.admin.AdminUser;
import com.andy.project1.util.BSResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;

@Service
public class AdminUserMgmtService {
    private final UserDao userDao;

    @Autowired
    public AdminUserMgmtService(UserDao userDao) {
        this.userDao = userDao;
    }

    public List<AdminUser> getAllUserForDisplay(){
        List<User> allUser = userDao.getAllUser();
        List<AdminUser> result = new LinkedList<>();
        for(User user : allUser){
            result.add(new AdminUser(user));
        }
        return result;
    }

    public BSResult toggleStatus(Integer userId){
        User user = userDao.getUserById(userId);
        if(user == null)
            return new BSResult(false, "No such user");
        if(user.getIs_admin())
            return new BSResult(false, "Cannot toggle status of admin");
        user.setIs_active(!user.getIs_active());
        int res = userDao.updateUser(user);
        if(res > 0)
            return new BSResult(true, "Toggle succeeded!");
        else
            return new BSResult(false, "Toggle failed. Try later.");
    }
}
