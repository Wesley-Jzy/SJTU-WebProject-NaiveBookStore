package com.easybookstore.DAO;

import com.easybookstore.entity.Book;

import java.util.List;

/**
 * Created by wesley on 17/4/19.
 */
public interface BookDAO {
    public long getCount();
    public List<Book> getList(int offset, int rows);
    public void createOrUpdate(Book book);
    public void delete(long id);
    public Book getInstanceById(long id);
}
