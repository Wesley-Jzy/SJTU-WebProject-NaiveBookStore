package com.easybookstore.controller;

import com.easybookstore.entity.User;
import com.easybookstore.service.RegisterService;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;

/**
 * Created by wesley on 17/4/25.
 */

@Controller
@RequestMapping(value = "/auth")
public class AuthController {

    @Autowired
    private RegisterService registerService;

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

        model.addAttribute("msg", "用户名或密码错误！");

        return "/auth/login.jsp";
    }

    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public String register() {
        return "/auth/register.jsp";
    }

    @ResponseBody
    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public void do_register(HttpServletResponse response, @RequestBody String json) {
        HashMap res = new HashMap<String, Object>();
        Gson gson = new Gson();

        System.out.println(json);

        User user = gson.fromJson(json, User.class);

        String result = registerService.register(user);

        if (result == "success") {
            res.put("success", true);
            res.put("msg", "注册成功");
        } else {
            res.put("success", false);
            res.put("msg", result);
        }

        try {
            response.setContentType("text/html; charset=utf-8");
            response.getWriter().print(gson.toJson(res));
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
