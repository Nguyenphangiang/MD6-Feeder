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

    @Column(name = "recommend")
    private boolean recommend;

    public boolean isRecommend() {
        return recommend;
    }

    public void setRecommend(boolean recommend) {
        this.recommend = recommend;
    }

    @ManyToOne
    @JoinColumn (name = "merchant_id")
    private Merchant merchant;
    @ManyToOne
    @JoinColumn(name = "status_id")
    private DishStatus status;
    public Dish() {
    }

    public Dish(String image, String name, String description, Long price, DishStatus status, Merchant merchant) {
        this.image = image;
        this.name = name;
        this.description = description;
        this.price = price;
        this.status = status;
        this.merchant = merchant;
    }

    public Dish(Long id, String image, String name, String description, Long price, DishStatus status, Merchant merchant) {
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

    public DishStatus getDishStatus() {
        return status;
    }

    public void setDishStatus(DishStatus status) {
        this.status = status;
    }

    public Merchant getMerchant() {
        return merchant;
    }

    public void setMerchant_id(Merchant merchant) {
        this.merchant = merchant;
    }
}
