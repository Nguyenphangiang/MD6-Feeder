package com.lunch.appfeeder.model.entity.DTO;
import com.lunch.appfeeder.model.entity.merchant.MerchantStatus;
import com.lunch.appfeeder.model.login.AppUser;
import org.springframework.web.multipart.MultipartFile;

public class MerchantForm {
    private Long id;
    private String name;
    private MultipartFile safeFoodLicense;
    private String email;
    private String phone;
    private String address;
    private AppUser user;
    private MerchantStatus status;

    public MerchantStatus getStatus() {
        return status;
    }

    public void setStatus(MerchantStatus status) {
        this.status = status;
    }

    public MerchantForm(String name, MultipartFile safeFoodLicense, String email, String phone, String address, AppUser user, MerchantStatus status) {
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

    public MultipartFile getSafeFoodLicense() {
        return safeFoodLicense;
    }

    public void setSafeFoodLicense(MultipartFile safeFoodLicense) {
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

    public MerchantForm() {
    }

    public MerchantForm(Long id, String name, MultipartFile safeFoodLicense, String email, String phone, String address, AppUser user, MerchantStatus status) {
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

