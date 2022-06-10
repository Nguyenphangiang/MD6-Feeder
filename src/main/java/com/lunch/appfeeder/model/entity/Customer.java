package com.lunch.appfeeder.model.entity;

import com.lunch.appfeeder.model.login.AppUser;

import javax.persistence.*;

@Entity
@Table(name = "customers")
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String email;

    private String phone;

    private String address;

    @OneToOne
    @JoinColumn(name = "user_id")
    private AppUser appUser;



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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public AppUser getAppUser() {
        return appUser;
    }

    public void setAppUser(AppUser appUser) {
        this.appUser = appUser;
    }

    public Customer() {

    }

    public Customer(String name, String email, String phone, String address, AppUser appUser) {
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.address = address;
        this.appUser = appUser;
    }

    public Customer(Long id, String name, String email, String phone, String address, AppUser appUser) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.address = address;
        this.appUser = appUser;
    }
}
