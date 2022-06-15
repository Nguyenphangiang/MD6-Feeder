package com.lunch.appfeeder.service.user;


import com.lunch.appfeeder.model.login.AppUser;
import com.lunch.appfeeder.service.IGeneralService;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface IAppUserService extends IGeneralService<AppUser>, UserDetailsService {
    AppUser findByUsername(String username);

    AppUser save(AppUser appUser, String siteURL, String email, String name);

    Iterable<AppUser> findAll();

    AppUser saveAdmin(AppUser appUser);

    AppUser saveMerchant(AppUser appUser, String siteURL, String email, String name);

    boolean verify(String verificationCode);

    boolean existsAppUsersByUsername(String name);
}
