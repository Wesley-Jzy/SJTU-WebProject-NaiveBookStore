package com.easybookstore.controller;

import com.easybookstore.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by wesley on 17/4/18.
 */

@Controller
public class IndexController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/")
    public String redirect_index()
    {
        return "redirect:/index";
    }

    @RequestMapping(value = "/test")
    public String test()
    {
        return "test.jsp";
    }

    @RequestMapping(value = "/index")
    public ModelAndView index()
    {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("index.jsp");

        return mv;
    }
}
