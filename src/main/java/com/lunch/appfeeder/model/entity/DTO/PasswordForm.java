package com.lunch.appfeeder.model.entity.DTO;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

public class PasswordForm {
    @NotEmpty
    @Size(min = 5, max = 12)
    private String password;

    @NotEmpty
    @Size(min = 5, max = 12)
    private String confirmPassword;

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

    public PasswordForm(String password, String confirmPassword) {
        this.password = password;
        this.confirmPassword = confirmPassword;
    }

    public PasswordForm() {
    }
}
