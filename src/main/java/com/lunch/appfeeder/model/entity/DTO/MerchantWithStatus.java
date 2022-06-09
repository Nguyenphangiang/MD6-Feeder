package com.lunch.appfeeder.model.entity.DTO;

import com.lunch.appfeeder.model.entity.merchant.MerchantStatus;
import com.lunch.appfeeder.model.login.AppUser;


import java.util.List;

public class MerchantWithStatus {
    private Long id;

    private String name;

    private String safeFoodLicense;

    private String email;

    private String phone;

    private String address;

    private List<MerchantStatus> statuses;

    public List<MerchantStatus> getStatuses() {
        return statuses;
    }

    public void setStatuses(List<MerchantStatus> statuses) {
        this.statuses = statuses;
    }

    public MerchantWithStatus(String name, String safeFoodLicense, String email, String phone, String address, List<MerchantStatus> statuses, AppUser user) {
        this.name = name;
        this.safeFoodLicense = safeFoodLicense;
        this.email = email;
        this.phone = phone;
        this.address = address;
        this.statuses = statuses;
        this.user = user;
    }

    public MerchantWithStatus(Long id, String name, String safeFoodLicense, String email, String phone, String address, List<MerchantStatus> statuses, AppUser user) {
        this.id = id;
        this.name = name;
        this.safeFoodLicense = safeFoodLicense;
        this.email = email;
        this.phone = phone;
        this.address = address;
        this.statuses = statuses;
        this.user = user;
    }

    private AppUser user;

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

    public MerchantWithStatus() {
    }
}
