package com.easybookstore.entity;

import com.google.gson.annotations.Expose;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by wesley on 17/4/19.
 */

@Entity(name = "users")
public class User implements Serializable {

    /* The primary key */
    @Expose
    @Id
    @Column(name = "user_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    /* username for login */
    @Expose
    @Column(name = "username", unique = true)
    private String username;

    /* Encrypted password */
    @Expose
    @Column(name = "password")
    private String password;

    /* User's email with checked format but not made sure the existence */
    @Expose
    @Column(name = "email")
    private String email;

    /* A naive way to save auth, a better way is admin the Role */
    @Expose
    @Column(name = "isAdmin")
    private int isAdmin;

    /* One to many for Order */
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    public Set<Order> orders = new HashSet<Order>();

    /* setter and getter */
    public void setId(long id) {
        this.id = id;
    }

    public long getId() {
        return this.id;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUsername() {
        return this.username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPassword() {
        return this.password;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEmail() {
        return this.email;
    }

    public void setIsAdmin(Integer isAdmin) {
        this.isAdmin = isAdmin;
    }

    public int getIsAdmin() {
        return this.isAdmin;
    }

    public void setOrders(Set<Order> orders) {
        this.orders = orders;
    }

    public Set<Order> getOrders() {
        return orders;
    }

}
