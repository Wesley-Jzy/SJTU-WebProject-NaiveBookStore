package com.easybookstore.controller;

import com.easybookstore.entity.Book;
import com.easybookstore.service.BookService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;


/**
 * Created by wesley on 17/4/20.
 */

@Controller
@RequestMapping(value = "/admin/book")
public class AdminBookController {

    @Autowired
    private BookService bookService;

    @RequestMapping(value = "")
    public String book_management() {
        return "admin/book.jsp";
    }

    @RequestMapping(value = "/list")
    @ResponseBody
    public void list(HttpServletRequest request, HttpServletResponse response) {
        HashMap res = new HashMap<String, Object>();
        String check;
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
        int offset = (page - 1) * rows;

        res.put("total", bookService.getCount());
        res.put("rows", bookService.getList(offset, rows));

        Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();

        System.out.println("oops" + gson.toJson(res));

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

        Book book = gson.fromJson(json, Book.class);

        bookService.createOrUpdate(book);

        res.put("success", true);

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
            bookService.delete(id);
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
