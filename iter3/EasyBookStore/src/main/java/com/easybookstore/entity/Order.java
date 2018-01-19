package com.easybookstore.entity;

import com.google.gson.annotations.Expose;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by wesley on 17/4/23.
 */

@Entity(name = "orders")
public class Order implements Serializable {

    /* The primary key */
    @Expose
    @Id
    @Column(name = "order_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    /* When the order is created */
    @Expose
    @Column(name = "create_time")
    private String createTime;

    /* 0 for not handled, 1 for handling, 2 for has finished */
    @Expose
    @Column(name = "status")
    private int status;

    /* Many to one for User */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    /* One to many for OrderItem */
    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    private Set<OrderItem> orderItems = new HashSet<OrderItem>();

    @Expose
    @Transient
    private long user_id;

    /* setter and getter */
    public void setId(long id) {
        this.id = id;
    }

    public long getId() {
        return this.id;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getCreateTime() {
        return this.createTime;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getStatus() {
        return this.status;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public User getUser() {
        return user;
    }

    public void setOrderItems(Set<OrderItem> orderItems) {
        this.orderItems = orderItems;
    }

    public Set<OrderItem> getOrderItems() {
        return orderItems;
    }

    public void setUser_id(long user_id) {
        this.user_id = user_id;
    }

    public long getUser_id() {
        return this.user_id;
    }
}
