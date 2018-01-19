package com.easybookstore.DAO;

import com.easybookstore.entity.Book;
import org.hibernate.Query;
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

        for (Book book : books) {
            double price = ((double)book.getPriceInt()) / 100;
            book.setPrice(price);
        }

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
        int priceInt = (int)(book.getPrice() * 100);
        book.setPriceInt(priceInt);
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

    public List<Book> proximitySearch(String s) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("select b from books b where b.name like :s" +
                " or b.ISBN like :s or b.author like :s or b.publishingHouse like :s");
        s = "%" + s + "%";
        query.setString("s", s);
        List<Book> books = (List<Book>) (query.list());

        for (Book book : books) {
            double price = ((double)book.getPriceInt()) / 100;
            book.setPrice(price);
        }

        return books;
    }
}
