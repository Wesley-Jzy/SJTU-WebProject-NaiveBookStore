package com.easybookstore.service;


import com.easybookstore.DAO.BookDAO;
import com.easybookstore.entity.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by wesley on 17/5/24.
 */

@Service
public class SearchServiceImpl implements SearchService {
    @Autowired
    private BookDAO bookDAO;

    public List<Book> proximityBookSearch(String s) {
        if (s.equals("")) {
            return null;
        }
        return bookDAO.proximitySearch(s);
    }
}
