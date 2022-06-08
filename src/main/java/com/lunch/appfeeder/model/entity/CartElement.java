package com.lunch.appfeeder.model.entity;

import javax.persistence.*;

@Entity
@Table(name = "carts")
public class CartElement {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long idCustomer;
    private Long idDish;
    private Long quantity;
    private String note ;

    public CartElement() {
    }

    public CartElement(Long idCustomer, Long idDish, Long quantity, String note) {
        this.idCustomer = idCustomer;
        this.idDish = idDish;
        this.quantity = quantity;
        this.note = note;
    }


    public CartElement(Long id, Long idCustomer, Long idDish, Long quantity, String note) {
        this.id = id;
        this.idCustomer = idCustomer;
        this.idDish = idDish;
        this.quantity = quantity;
        this.note = note;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getIdCustomer() {
        return idCustomer;
    }

    public void setIdCustomer(Long idCustomer) {
        this.idCustomer = idCustomer;
    }

    public Long getIdDish() {
        return idDish;
    }

    public void setIdDish(Long idDish) {
        this.idDish = idDish;
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
