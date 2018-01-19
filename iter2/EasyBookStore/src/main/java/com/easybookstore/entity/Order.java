package com.easybookstore.entity;

import com.google.gson.annotations.Expose;

import javax.persistence.*;
import java.io.Serializable;

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

    /* Quantity */
    @Expose
    @Column(name = "quantity")
    private int quantity;

    /* Many to one for User */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    /* Many to one for Book */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "book_id")
    private Book book;

    @Expose
    @Transient
    private long user_id;

    @Expose
    @Transient
    private long book_id;

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

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getQuantity() {
        return this.quantity;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public User getUser() {
        return user;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public Book getBook() {
        return book;
    }

    public long getUser_id() {
        return this.user_id;
    }

    public void setUser_id(long user_id) {
        this.user_id = user_id;
    }

    public long getBook_id() {
        return this.book_id;
    }

    public void setBook_id(long book_id) {
        this.book_id = book_id;
    }
}
