package com.easybookstore.service;

import com.easybookstore.DAO.BookDAO;
import com.easybookstore.entity.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by wesley on 17/4/23.
 */

@Service
public class BookServiceImpl implements BookService {

    @Autowired
    private BookDAO bookDAO;

    public List<Book> getList(int offset, int rows) {
        return bookDAO.getList(offset, rows);
    }

    public long getCount() {
        return bookDAO.getCount();
    }

    public void createOrUpdate(Book book) {
        bookDAO.createOrUpdate(book);
    }

    public void delete(long id) {
        bookDAO.delete(id);
    }
}
