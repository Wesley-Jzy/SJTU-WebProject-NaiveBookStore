package com.easybookstore.entity;

import com.google.gson.annotations.Expose;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by wesley on 17/4/21.
 */

@Entity(name = "books")
public class Book implements Serializable {

    /* The primary key */
    @Expose
    @Id
    @Column(name = "book_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    /* ISBN */
    @Expose
    @Column(name = "ISBN", unique = true)
    private String ISBN;

    /* Book name */
    @Expose
    @Column(name = "name")
    private String name;

    /* price */
    @Expose
    @Column(name = "price")
    private double price;

    /* quantity */
    @Expose
    @Column(name = "quantity")
    private int quantity;

    /* One to many for Order */
    @OneToMany(mappedBy = "book", cascade = CascadeType.ALL)
    public Set<Order> orders = new HashSet<Order>();

    /* setter and getter */
    public void setId(long id) {
        this.id = id;
    }

    public long getId() { return this.id;}

    public void setISBN(String ISBN) { this.ISBN = ISBN;}

    public String getISBN() { return this.ISBN;}

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getPrice() {
        return this.price;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getQuantity() {
        return this.quantity;
    }

    public void setOrders(Set<Order> orders) {
        this.orders = orders;
    }

    public Set<Order> getOrders() {
        return orders;
    }
}
