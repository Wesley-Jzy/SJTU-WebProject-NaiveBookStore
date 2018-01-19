package com.easybookstore.controller;

import com.easybookstore.entity.Order;
import com.easybookstore.entity.OrderItem;
import com.easybookstore.service.OrderItemService;
import com.easybookstore.service.OrderService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;

/**
 * Created by wesley on 17/5/4.
 */

@Controller
@RequestMapping(value = "/admin/orderItem")
public class AdminOrderItemController {

    @Autowired
    private OrderService orderService;

    @Autowired
    private OrderItemService orderItemService;

    @RequestMapping(value = "")
    public ModelAndView orderItem_management(HttpServletRequest request) {
        String s = request.getParameter("order_id");
        long id = Long.parseLong(s);

        ModelAndView mv = new ModelAndView();
        mv.setViewName( "admin/orderItem.jsp");
        mv.addObject("order_id", id);

        return mv;
    }

    @RequestMapping(value = "/list")
    @ResponseBody
    public void list(HttpServletRequest request, HttpServletResponse response) {
        System.out.println(request.getRequestURL());
        HashMap res = new HashMap<String, Object>();
        String check;
        long order_id;
        int page, rows;
        check = request.getParameter("page");
        if (check != null) {
            page = Integer.parseInt(check);
        } else {
            page = 1;
        }
        check = request.getParameter("rows");
        if (check != null) {
            rows = Integer.parseInt(check);
        } else {
            rows = 10;
        }
        check = request.getParameter("order_id");
        if (check != null) {
            order_id = Long.parseLong(check);
        } else {
            order_id = -1;
        }
        int offset = (page - 1) * rows;

        Order order = orderService.getInstanceById(order_id);
        if (order == null) {
            res.put("total", 0);
            res.put("rows", null);
        } else {
            res.put("total", orderItemService.getCountByOrderId(order_id));
            res.put("rows", orderItemService.getListByOrderId(offset, rows, order_id));
        }

        Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();

        System.out.println(gson.toJson(res));

        try {
            response.setContentType("text/html; charset=utf-8");
            response.getWriter().print(gson.toJson(res));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @RequestMapping(value = "/save")
    @ResponseBody
    public void save(HttpServletResponse response, @RequestBody String json) {
        HashMap res = new HashMap<String, Object>();
        Gson gson = new Gson();

        OrderItem orderItem = gson.fromJson(json, OrderItem.class);

        String result = orderItemService.createOrUpdate(orderItem);

        if (result == "success") {
            res.put("success", true);
        } else {
            res.put("success", false);
            res.put("errorMsg", result);
        }

        try {
            response.setContentType("text/html; charset=utf-8");
            response.getWriter().print(gson.toJson(res));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @RequestMapping(value = "/remove")
    @ResponseBody
    public void remove(HttpServletResponse response, HttpServletRequest request) {
        HashMap res = new HashMap<String, Object>();
        String check;
        long id;
        check = request.getParameter("id");
        if (check != null) {
            id = Long.parseLong(check);
            orderItemService.delete(id);
            res.put("success", true);

        } else {
            res.put("success", false);
            res.put("errorMsg", "缺少id参数");
        }

        try {
            response.setContentType("text/html; charset=utf-8");
            response.getWriter().print(new Gson().toJson(res));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
