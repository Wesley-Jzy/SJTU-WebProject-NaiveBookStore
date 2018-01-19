package com.easybookstore.controller;

import com.easybookstore.entity.Book;
import com.easybookstore.service.SearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created by wesley on 17/5/24.
 */

@Controller
@RequestMapping(value = "/search")
public class SearchController {
    @Autowired
    private SearchService searchService;

    @RequestMapping(value = "")
    public ModelAndView forwardSearch(HttpServletRequest request) {
        ModelAndView mv = new ModelAndView();
        String query = request.getParameter("query");
        if (query == null) {
            mv.setViewName("search/fail.jsp");
            mv.addObject("query",query);
            return mv;
        }

        List<Book> books = searchService.proximityBookSearch(query);
        if (books == null || books.isEmpty()) {
            mv.setViewName("search/fail.jsp");
            mv.addObject("query",query);
            return mv;
        }
        else {
            mv.setViewName("search/success.jsp");
            mv.addObject("bookList",books);
            return mv;
        }
    }
}
