package com.easybookstore.service;

import com.easybookstore.DAO.BookDAO;
import com.easybookstore.DAO.OrderDAO;
import com.easybookstore.DAO.OrderItemDAO;
import com.easybookstore.DAO.UserDAO;
import com.easybookstore.entity.Book;
import com.easybookstore.entity.Order;
import com.easybookstore.entity.OrderItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by wesley on 17/4/23.
 */

@Service
public class OrderItemServiceImpl implements OrderItemService {

    @Autowired
    private OrderDAO orderDAO;

    @Autowired
    private OrderItemDAO orderItemDAO;

    @Autowired
    private BookDAO bookDAO;

    @Autowired
    private UserDAO userDAO;

    public List<OrderItem> getListByOrderId(int offset, int rows, long order_id) {
        return orderItemDAO.getListByOrderId(offset, rows, order_id);
    }

    public long getCountByOrderId(long order_id) {
        return orderItemDAO.getCountByOrderId(order_id);
    }

    public String createOrUpdate(OrderItem orderItem) {
        String result = "success";
        long book_id = orderItem.getBook_id();
        long order_id = orderItem.getOrder_id();
        int quantity = orderItem.getQuantity();

        Book book = bookDAO.getInstanceById(book_id);
        Order order = orderDAO.getInstanceById(order_id);

        if (order == null) {
            result = "该订单号不存在";
        } else if (book == null) {
            result = "该书籍不存在";
        } else if (quantity <= 0) {
            result = "购买数量不对";
        } else {
            orderItem.setBook(book);
            orderItem.setOrder(order);
            orderItemDAO.createOrUpdate(orderItem);
        }

        return result;
    }

    public void delete(long id) {
        orderItemDAO.delete(id);
    }
}
