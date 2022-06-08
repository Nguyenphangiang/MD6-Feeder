package com.lunch.appfeeder.model.order;

import com.lunch.appfeeder.model.dish.Dish;
import com.lunch.appfeeder.model.entity.Customer;

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

    @OneToMany
    @JoinColumn
    private List<Dish> dishList;
    private int quantity;
    private String note;
    private int status;

    public Order() {
    }

    public Order(Customer customer, Date orderTime, List<Dish> dishList, int quantity, String note, int status) {
        this.customer = customer;
        this.orderTime = orderTime;
        this.dishList = dishList;
        this.quantity = quantity;
        this.note = note;
        this.status = status;
    }

    public Order(Long id, Customer customer, Date orderTime, List<Dish> dishList, int quantity, String note, int status) {
        this.id = id;
        this.customer = customer;
        this.orderTime = orderTime;
        this.dishList = dishList;
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

    public List<Dish> getDishList() {
        return dishList;
    }

    public void setDishList(List<Dish> dishList) {
        this.dishList = dishList;
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
