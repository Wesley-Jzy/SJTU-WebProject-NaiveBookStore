package com.easybookstore.DAO;

import com.easybookstore.entity.OrderItem;
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
public class OrderItemDAOImpl implements OrderItemDAO  {

    @Autowired
    private SessionFactory sessionFactory;

    public List<OrderItem> getListByOrderId(int offset, int rows, long order_id) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("select item from orderItems item " +
                "where item.order = :order_id")
                .setFirstResult(offset)
                .setMaxResults(rows);
        query.setLong("order_id", order_id);
        List<OrderItem> orderItems = (List<OrderItem>) (query.list());

        for (OrderItem orderItem: orderItems) {
            orderItem.setBook_id(orderItem.getBook().getId());
            orderItem.setOrder_id(orderItem.getOrder().getId());
        }

        return orderItems;
    }

    public long getCountByOrderId(long order_id) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("select count(*) from orderItems item " +
                "where item.order = :order_id");
        query.setLong("order_id", order_id);
        long count = (long)(Long)(query.uniqueResult());;
        return count;
    }

    public void createOrUpdate(OrderItem orderItem) {
        if (orderItem == null) {
            System.out.println("null order");
            return;
        }
        Session session = sessionFactory.getCurrentSession();
        session.saveOrUpdate(orderItem);
        session.flush();
        return;
    }

    public void delete(long id) {

        Session session = sessionFactory.getCurrentSession();
        OrderItem orderItem = (OrderItem) session.load(OrderItem.class, id);
        session.delete(orderItem);
        session.flush();
        return;
    }

}
