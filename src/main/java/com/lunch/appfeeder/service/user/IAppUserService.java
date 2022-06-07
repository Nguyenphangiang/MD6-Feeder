package com.lunch.appfeeder.service.user;


import com.lunch.appfeeder.model.login.AppUser;
import com.lunch.appfeeder.service.IGeneralService;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface IAppUserService extends IGeneralService<AppUser>, UserDetailsService {
    AppUser findByUsername(String username);

    Iterable<AppUser> findAll();

    AppUser saveAdmin(AppUser appUser);
}
