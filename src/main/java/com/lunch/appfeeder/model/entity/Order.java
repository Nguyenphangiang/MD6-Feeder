package com.lunch.appfeeder.model.entity;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table (name = "orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn (name = "customer_id")
    private Customer customer;
    private Date orderTime;

    @OneToOne
    @JoinColumn (name = "dish_id")
    private Dish dish;
    private int quantity;
    private String note;
    private int status;

    public Order() {
    }

    public Order(Customer customer, Date orderTime, Dish dish, int quantity, String note, int status) {
        this.customer = customer;
        this.orderTime = orderTime;
        this.dish = dish;
        this.quantity = quantity;
        this.note = note;
        this.status = status;
    }

    public Order(Long id, Customer customer, Date orderTime, Dish dish, int quantity, String note, int status) {
        this.id = id;
        this.customer = customer;
        this.orderTime = orderTime;
        this.dish = dish;
        this.quantity = quantity;
        this.note = note;
        this.status = status;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Date getOrderTime() {
        return orderTime;
    }

    public void setOrderTime(Date orderTime) {
        this.orderTime = orderTime;
    }

    public Dish getDish() {
        return dish;
    }

    public void setDish(Dish dish) {
        this.dish = dish;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
