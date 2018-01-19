package com.easybookstore.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by wesley on 17/4/20.
 */

@Controller
@RequestMapping(value = "/admin")
public class AdminController {

    @RequestMapping(value = "")
    public String redirect_home() {
        return "redirect:/admin/home";
    }

    @RequestMapping(value = "/home")
    public String home() {
        return "admin/home.jsp";
    }

}
