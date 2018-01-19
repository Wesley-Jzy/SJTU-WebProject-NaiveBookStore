package com.easybookstore.service;

import com.easybookstore.DAO.UserDAO;
import com.easybookstore.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by wesley on 17/5/3.
 */

@Service
public class RegisterServiceImpl implements RegisterService {

    @Autowired
    private UserDAO userDAO;

    public String register(User user) {
        String result = "success";
        String username = user.getUsername();
        String password = user.getPassword();
        String email = user.getEmail();
        User existedUser = userDAO.getInstanceByUsername(username);

        if (existedUser != null) {
            result = "用户名已存在";
        } else if (username.equals("")) {
            result = "用户名不能为空";
        } else if (password.length() < 4 || password.length() > 20) {
            result = "密码应为4-20位";
        } else if (email.equals("")) {
            result = "邮箱不能为空";
        } else {
            user.setIsAdmin(0);
            userDAO.createOrUpdate(user);
        }

        return result;
    }
}
