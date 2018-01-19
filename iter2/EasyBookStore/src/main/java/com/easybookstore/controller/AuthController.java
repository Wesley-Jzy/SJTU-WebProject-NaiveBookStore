package com.easybookstore.controller;

import com.easybookstore.entity.User;
import com.easybookstore.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Created by wesley on 17/4/25.
 */

@Controller
@RequestMapping(value = "/auth")
public class AuthController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login() {
        return "/auth/login.jsp";
    }

    @RequestMapping(value = "/denied")
    public String denied(ModelMap model) {

        model.addAttribute("msg", "权限不足！");

        return "error.jsp";
    }

    @RequestMapping(value = "/failed")
    public String failed(ModelMap model) {

        model.addAttribute("msg", "登陆失败！");

        return "error.jsp";
    }

    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public String register() {
        return "/auth/register.jsp";
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public String do_register(@RequestParam String username, @RequestParam String password) {
        User user = new User();
        user.setUsername(username);
        user.setPassword(password);
        user.setIsAdmin(0);

        userService.createOrUpdate(user);

        return "redirect:/auth/login";
    }
}
