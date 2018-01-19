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

    /* Category */
    @Expose
    @Column(name = "category")
    private String category;

    /* Author */
    @Expose
    @Column(name = "author")
    private String author;

    /* Publishing house */
    @Expose
    @Column(name = "publishing_house")
    private String publishingHouse;

    /* price with int, the real price should / 100 */
    @Column(name = "price")
    private int priceInt;

    /* the real price */
    @Expose
    @Transient
    private double price;

    /* quantity */
    @Expose
    @Column(name = "quantity")
    private int quantity;

    /* One to many for Order */
    @OneToMany(mappedBy = "book", cascade = CascadeType.ALL)
    private Set<OrderItem> orderItems = new HashSet<OrderItem>();

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

    public void setCategory(String category) {
        this.category = category;
    }

    public String getCategory() {
        return category;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getAuthor() {
        return author;
    }

    public void setPublishingHouse(String publishingHouse) {
        this.publishingHouse = publishingHouse;
    }

    public String getPublishingHouse() {
        return publishingHouse;
    }

    public void setPriceInt(int priceInt) {
        this.priceInt = priceInt;
    }

    public int getPriceInt() {
        return this.priceInt;
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

    public void setOrderItems(Set<OrderItem> orderItems) {
        this.orderItems = orderItems;
    }

    public Set<OrderItem> getOrderItems() {
        return orderItems;
    }
}
