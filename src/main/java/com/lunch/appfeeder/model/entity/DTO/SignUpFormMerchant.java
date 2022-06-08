package com.lunch.appfeeder.model.entity.DTO;

import com.lunch.appfeeder.validator.PasswordConfirm;
import com.lunch.appfeeder.validator.UniqueUsername;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

public class SignUpFormMerchant {
    @NotEmpty
    @UniqueUsername
    @Size(min = 5, max = 12)
    private String username;
    private String password;
    private String confirmPassword;
    private String email;
    private String name;
    private String phone;
    private String address;

    private MultipartFile safeFoodLicense;

    public MultipartFile getSafeFoodLicense() {
        return safeFoodLicense;
    }

    public void setSafeFoodLicense(MultipartFile safeFoodLicense) {
        this.safeFoodLicense = safeFoodLicense;
    }

    public SignUpFormMerchant(String username, String password, String confirmPassword, String email, String name, String phone, String address, MultipartFile safeFoodLicense, PasswordForm passwordForm) {
        this.username = username;
        this.password = password;
        this.confirmPassword = confirmPassword;
        this.email = email;
        this.name = name;
        this.phone = phone;
        this.address = address;
        this.safeFoodLicense = safeFoodLicense;
        this.passwordForm = passwordForm;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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


    @PasswordConfirm
    private PasswordForm passwordForm;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }






    public PasswordForm getPasswordForm() {
        return passwordForm;
    }

    public void setPasswordForm(PasswordForm passwordForm) {
        this.passwordForm = passwordForm;
    }


}
