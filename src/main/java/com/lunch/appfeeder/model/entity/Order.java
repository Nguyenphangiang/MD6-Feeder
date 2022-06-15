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


    public Order() {
    }

    public Order( Dish dish, int quantity) {
        this.dish = dish;
        this.quantity = quantity;
    }

    public Order(Long id, Dish dish, int quantity) {
        this.id = id;
        this.dish = dish;
        this.quantity = quantity;
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
