package com.lunch.appfeeder.model.entity;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToOne
    @JoinColumn(name = "dish_id")
    private Dish dish;
    private int quantity;
    @Column(name = "ordercheck")
    private boolean orderCheck;
    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;

    public Order(Dish dish, int quantity, boolean orderCheck, Customer customer) {
        this.dish = dish;
        this.quantity = quantity;
        this.orderCheck = orderCheck;
        this.customer = customer;
    }

    public Order(Long id) {
        this.id = id;
    }

    public boolean isOrderCheck() {
        return orderCheck;
    }

    public void setOrderCheck(boolean orderCheck) {
        this.orderCheck = orderCheck;
    }

    public Order(Long id, Dish dish, int quantity, boolean orderCheck, Customer customer) {
        this.id = id;
        this.dish = dish;
        this.quantity = quantity;
        this.orderCheck = orderCheck;
        this.customer = customer;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Order() {
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
}
