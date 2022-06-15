package com.lunch.appfeeder.model.entity.address;

import com.lunch.appfeeder.model.entity.Customer;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "order_address")
public class OrderAddress {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String type;

    private String name;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;

    public OrderAddress() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public OrderAddress(String type, String name, Customer customer) {
        this.type = type;
        this.name = name;
        this.customer = customer;
    }

    public OrderAddress(Long id, String type, String name, Customer customer) {
        this.id = id;
        this.type = type;
        this.name = name;
        this.customer = customer;
    }
}
