package com.easybookstore.DAO;

import com.easybookstore.entity.OrderItem;

import java.util.List;

/**
 * Created by wesley on 17/4/19.
 */
public interface OrderItemDAO {
    public long getCountByOrderId(long order_id);
    public List<OrderItem> getListByOrderId(int offset, int rows, long order_id);
    public void createOrUpdate(OrderItem orderItem);
    public void delete(long id);
}
