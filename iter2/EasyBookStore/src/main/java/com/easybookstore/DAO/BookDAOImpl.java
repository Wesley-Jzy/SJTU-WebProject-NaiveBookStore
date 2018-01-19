package com.easybookstore.DAO;

import com.easybookstore.entity.Book;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by wesley on 17/4/19.
 */

@Repository
public class BookDAOImpl implements BookDAO  {

    @Autowired
    private SessionFactory sessionFactory;

    public List<Book> getList(int offset, int rows) {
        Session session = sessionFactory.getCurrentSession();
        List<Book> books = (List<Book>) (session.createQuery("select b from books b")
                .setFirstResult(offset)
                .setMaxResults(rows)
                .list());
        return books;
    }

    public long getCount() {
        Session session = sessionFactory.getCurrentSession();
        long count = (long)(Long)(session.createQuery("select count(*) from books")
                    .uniqueResult());
        return count;
    }

    public void createOrUpdate(Book book) {
        if (book == null) {
            System.out.println("null book");
            return;
        }
        Session session = sessionFactory.getCurrentSession();
        session.saveOrUpdate(book);
        session.flush();
        return;
    }

    public void delete(long id) {

        Session session = sessionFactory.getCurrentSession();
        Book book = (Book) session.load(Book.class, id);
        session.delete(book);
        session.flush();
        return;
    }

    public Book getInstanceById(long id) {
        Session session = sessionFactory.getCurrentSession();
        Book book = (Book) session.get(Book.class, id);
        return book;
    }
}
