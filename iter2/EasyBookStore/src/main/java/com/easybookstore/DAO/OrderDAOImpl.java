package com.easybookstore.DAO;

import com.easybookstore.entity.Book;
import com.easybookstore.entity.Order;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by wesley on 17/4/19.
 */

@Repository
public class OrderDAOImpl implements OrderDAO  {

    @Autowired
    private SessionFactory sessionFactory;

    public List<Order> getList(int offset, int rows) {
        Session session = sessionFactory.getCurrentSession();
        List<Order> orders = (List<Order>) (session.createQuery("select o from orders o")
                .setFirstResult(offset)
                .setMaxResults(rows)
                .list());

        for (Order order: orders) {
            order.setUser_id(order.getUser().getId());
            order.setBook_id(order.getBook().getId());
        }

        return orders;
    }

    public long getCount() {
        Session session = sessionFactory.getCurrentSession();
        long count = (long)(Long)(session.createQuery("select count(*) from orders")
                    .uniqueResult());
        return count;
    }

    public void createOrUpdate(Order order) {
        if (order == null) {
            System.out.println("null order");
            return;
        }
        Session session = sessionFactory.getCurrentSession();
        session.saveOrUpdate(order);
        session.flush();
        return;
    }

    public void delete(long id) {

        Session session = sessionFactory.getCurrentSession();
        Order order = (Order) session.load(Order.class, id);
        session.delete(order);
        session.flush();
        return;
    }

}
