package com.easybookstore.entity;

import com.google.gson.annotations.Expose;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by wesley on 17/5/4.
 */

@Entity(name = "orderItems")
public class OrderItem implements Serializable {

    /* The primary key */
    @Expose
    @Id
    @Column(name = "orderItem_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    /* The quantity of book */
    @Expose
    @Column(name = "quantity")
    private int quantity;

    /* Many to one for Book */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "book_id")
    private Book book;

    /* Many to one for Order */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id")
    private Order order;

    @Expose
    @Transient
    private long book_id;

    @Expose
    @Transient
    private long order_id;

    /* setter and getter */
    public void setId(long id) {
        this.id = id;
    }

    public long getId() {
        return this.id;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getQuantity() {
        return this.quantity;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public Book getBook() {
        return this.book;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public Order getOrder() {
        return this.order;
    }

    public void setBook_id(long book_id) {
        this.book_id = book_id;
    }

    public long getBook_id() {
        return this.book_id;
    }

    public void setOrder_id(long order_id) {
        this.order_id = order_id;
    }

    public long getOrder_id() {
        return this.order_id;
    }
}
