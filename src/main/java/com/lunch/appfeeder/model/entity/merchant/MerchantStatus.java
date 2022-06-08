package com.lunch.appfeeder.model.entity.merchant;

import javax.persistence.*;

@Entity
@Table(name="merchant_status")
public class MerchantStatus {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;


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

    public MerchantStatus() {
    }

    public MerchantStatus(Long id, String name) {
        this.id = id;
        this.name = name;
    }
}
