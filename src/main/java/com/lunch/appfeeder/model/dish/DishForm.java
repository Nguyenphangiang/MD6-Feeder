package com.lunch.appfeeder.model.dish;

import org.springframework.web.multipart.MultipartFile;

public class DishForm {
    private Long id;
    private MultipartFile image;
    private String name;
    private String description;
    private Long price;
    private String status;
    private Long merchant_id;

    public DishForm() {
    }

    public DishForm(MultipartFile image, String name, String description, Long price, String status, Long merchant_id) {
        this.image = image;
        this.name = name;
        this.description = description;
        this.price = price;
        this.status = status;
        this.merchant_id = merchant_id;
    }

    public DishForm(Long id, MultipartFile image, String name, String description, Long price, String status, Long merchant_id) {
        this.id = id;
        this.image = image;
        this.name = name;
        this.description = description;
        this.price = price;
        this.status = status;
        this.merchant_id = merchant_id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public MultipartFile getImage() {
        return image;
    }

    public void setImage(MultipartFile image) {
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

    public Long getMerchant_id() {
        return merchant_id;
    }

    public void setMerchant_id(Long merchant_id) {
        this.merchant_id = merchant_id;
    }
}
