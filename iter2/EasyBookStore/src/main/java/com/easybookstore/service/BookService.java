package com.easybookstore.service;

import com.easybookstore.entity.Book;

import java.util.List;

/**
 * Created by wesley on 17/4/23.
 */

public interface BookService {
    public long getCount();
    public List<Book> getList(int offset, int rows);
    public void createOrUpdate(Book book);
    public void delete(long id);
}
