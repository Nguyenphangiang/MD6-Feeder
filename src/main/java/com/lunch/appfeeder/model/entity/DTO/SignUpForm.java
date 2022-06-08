package com.lunch.appfeeder.model.entity.DTO;



import com.lunch.appfeeder.validator.PasswordConfirm;
import com.lunch.appfeeder.validator.UniqueUsername;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

public class SignUpForm {
    @NotEmpty
    @UniqueUsername
    @Size(min = 5, max = 12)
    private String username;
    private String password;
    private String confirmPassword;
    private String email;
    private String fullName;


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

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }




    public PasswordForm getPasswordForm() {
        return passwordForm;
    }

    public void setPasswordForm(PasswordForm passwordForm) {
        this.passwordForm = passwordForm;
    }

    public SignUpForm(String username, String password, String confirmPassword, String email, String fullName, PasswordForm passwordForm) {
        this.username = username;
        this.password = password;
        this.confirmPassword = confirmPassword;
        this.email = email;
        this.fullName = fullName;
        this.passwordForm = passwordForm;
    }

    public SignUpForm() {
    }
}
