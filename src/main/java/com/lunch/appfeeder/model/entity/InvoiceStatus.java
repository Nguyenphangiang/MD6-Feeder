package com.lunch.appfeeder.model.entity;

import javax.persistence.*;

@Entity
@Table(name = "order_status")
public class InvoiceStatus {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    public InvoiceStatus(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public InvoiceStatus(String name) {
        this.name = name;
    }

    public InvoiceStatus() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
