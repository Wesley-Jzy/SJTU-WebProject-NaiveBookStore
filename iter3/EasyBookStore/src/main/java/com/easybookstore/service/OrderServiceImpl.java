package com.easybookstore.service;

import com.easybookstore.DAO.BookDAO;
import com.easybookstore.DAO.OrderDAO;
import com.easybookstore.DAO.UserDAO;
import com.easybookstore.entity.Order;
import com.easybookstore.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by wesley on 17/4/23.
 */

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderDAO orderDAO;

    @Autowired
    private BookDAO bookDAO;

    @Autowired
    private UserDAO userDAO;

    public List<Order> getList(int offset, int rows) {
        return orderDAO.getList(offset, rows);
    }

    public long getCount() {
        return orderDAO.getCount();
    }

    public String createOrUpdate(Order order) {
        String result = "success";
        long user_id = order.getUser_id();
        int status = order.getStatus();

        User user = userDAO.getInstanceById(user_id);

        if (user == null) {
            result = "该用户不存在";
        } else if (status != 0 && status != 1 && status != 2) {
            result = "订单状态不存在";
        } else {
            SimpleDateFormat timeFormatter = new SimpleDateFormat("yyyy-MM-dd,hh:mm:ss");
            Date curDate = new Date(System.currentTimeMillis());
            String createTime = timeFormatter.format(curDate);
            order.setCreateTime(createTime);
            order.setUser(user);
            orderDAO.createOrUpdate(order);
        }

        return result;
    }

    public void delete(long id) {
        orderDAO.delete(id);
    }

    public Order getInstanceById(long id) {
        return orderDAO.getInstanceById(id);
    }
}
