package com.lunch.appfeeder.model.entity.DTO;


import com.lunch.appfeeder.model.login.AppRole;
import com.lunch.appfeeder.model.login.AppUser;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class UserPrincipal implements UserDetails {
    private AppUser appUser;

    private Long id;

    private String username;

    private String password;

    private List<? extends GrantedAuthority> appRoles;

    public UserPrincipal(AppUser appUser){
        this.appUser = appUser;
    }

    public AppUser getAppUser() {
        return appUser;
    }

    public void setAppUser(AppUser appUser) {
        this.appUser = appUser;
    }
    @Override
    public boolean isEnabled() {
        return true;
    }
    public UserPrincipal(Long id, String username, String password, List<? extends GrantedAuthority> appRoles) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.appRoles = appRoles;
    }

    public static UserPrincipal build(AppUser appUser) {
        //Lấy ra role của user
        List<AppRole> appRoles = appUser.getRoles();
        //tạo list quyền cho user principal
        List<GrantedAuthority> authorities = new ArrayList<>();
        for (AppRole appRole: appRoles) {
            //thêm quyền vào list
            authorities.add(new SimpleGrantedAuthority(appRole.getName()));
        }
        return new UserPrincipal(
                appUser.getId(),
                appUser.getUsername(),
                appUser.getPassword(),
                authorities
        );
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return appRoles;
    }


    @Override
    public String getPassword() {
        return password;
    }


    @Override
    public String getUsername() {
        return username;
    }


    @Override
    public boolean isAccountNonExpired() {
        return true;
    }


    @Override
    public boolean isAccountNonLocked() {
        return true;
    }


    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<? extends GrantedAuthority> getAppRoles() {
        return appRoles;
    }

    public void setAppRoles(List<? extends GrantedAuthority> appRoles) {
        this.appRoles = appRoles;
    }
}
