package com.lunch.appfeeder.model.entity.merchant;

import com.lunch.appfeeder.model.login.AppUser;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="merchants")
public class Merchant {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String safeFoodLicense;

    private String email;

    private String phone;

    private String address;

    @OneToOne
    @JoinColumn(name = "user_id")
    private AppUser user;

    @OneToOne
    @JoinColumn(name = "status")
    private MerchantStatus status;

    public MerchantStatus getStatus() {
        return status;
    }

    public void setStatus(MerchantStatus status) {
        this.status = status;
    }

    public Merchant(String name, String safeFoodLicense, String email, String phone, String address, AppUser user, MerchantStatus status) {
        this.name = name;
        this.safeFoodLicense = safeFoodLicense;
        this.email = email;
        this.phone = phone;
        this.address = address;
        this.user = user;
        this.status = status;
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

    public String getSafeFoodLicense() {
        return safeFoodLicense;
    }

    public void setSafeFoodLicense(String safeFoodLicense) {
        this.safeFoodLicense = safeFoodLicense;
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

    public AppUser getUser() {
        return user;
    }

    public void setUser(AppUser user) {
        this.user = user;
    }

    public Merchant() {
    }

    public Merchant(Long id, String name, String safeFoodLicense, String email, String phone, String address, AppUser user, MerchantStatus status) {
        this.id = id;
        this.name = name;
        this.safeFoodLicense = safeFoodLicense;
        this.email = email;
        this.phone = phone;
        this.address = address;
        this.user = user;
        this.status = status;
    }
}
