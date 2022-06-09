package com.lunch.appfeeder.model.entity;

import com.lunch.appfeeder.model.entity.merchant.Merchant;

import javax.persistence.*;

@Entity
@Table( name = "dishes")
public class Dish {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)

    private Long id;
    private String image;
    private String name;
    private String description;
    private Long price;
    private String status;
    @ManyToOne
    @JoinColumn (name = "merchant_id")
    private Merchant merchant;

    public Dish() {
    }

    public Dish(String image, String name, String description, Long price, String status, Merchant merchant) {
        this.image = image;
        this.name = name;
        this.description = description;
        this.price = price;
        this.status = status;
        this.merchant = merchant;
    }

    public Dish(Long id, String image, String name, String description, Long price, String status, Merchant merchant) {
        this.id = id;
        this.image = image;
        this.name = name;
        this.description = description;
        this.price = price;
        this.status = status;
        this.merchant = merchant;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getPrice() {
        return price;
    }

    public void setPrice(Long price) {
        this.price = price;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Merchant getMerchant() {
        return merchant;
    }

    public void setMerchant_id(Merchant merchant) {
        this.merchant = merchant;
    }
}
