package com.easybookstore.service;

import com.easybookstore.entity.Order;

import java.util.List;

/**
 * Created by wesley on 17/4/23.
 */

public interface OrderService {
    public long getCount();
    public List<Order> getList(int offset, int rows);
    public String createOrUpdate(Order order);
    public void delete(long id);
}
