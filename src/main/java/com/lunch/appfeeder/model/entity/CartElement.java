package com.lunch.appfeeder.model.entity;

import com.lunch.appfeeder.model.dish.Dish;

import javax.persistence.*;

@Entity
@Table(name = "carts")
public class CartElement {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;
    @OneToOne
    private Dish dish;
    private Long quantity;
    private String note ;

    public CartElement() {
    }

    public CartElement(Customer customer, Dish dish, Long quantity, String note) {
        this.customer = customer;
        this.dish = dish;
        this.quantity = quantity;
        this.note = note;
    }

    public CartElement(Long id, Customer customer, Dish dish, Long quantity, String note) {
        this.id = id;
        this.customer = customer;
        this.dish = dish;
        this.quantity = quantity;
        this.note = note;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Dish getDish() {
        return dish;
    }

    public void setDish(Dish dish) {
        this.dish = dish;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


    public Long getQuantity() {
        return quantity;
    }

    public void setQuantity(Long quantity) {
        this.quantity = quantity;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }
}
