package com.easybookstore.service;

import com.easybookstore.entity.OrderItem;

import java.util.List;

/**
 * Created by wesley on 17/4/23.
 */

public interface OrderItemService {
    public long getCountByOrderId(long order_id);
    public List<OrderItem> getListByOrderId(int offset, int rows, long order_id);
    public String createOrUpdate(OrderItem orderItem);
    public void delete(long id);
}
