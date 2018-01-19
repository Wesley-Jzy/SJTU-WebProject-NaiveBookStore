package com.easybookstore.DAO;

import com.easybookstore.entity.Order;

import java.util.List;

/**
 * Created by wesley on 17/4/19.
 */
public interface OrderDAO {
    public long getCount();
    public List<Order> getList(int offset, int rows);
    public void createOrUpdate(Order order);
    public void delete(long id);
}
