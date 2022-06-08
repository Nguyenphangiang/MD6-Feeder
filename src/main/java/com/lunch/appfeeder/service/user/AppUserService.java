package com.lunch.appfeeder.service.user;


import com.lunch.appfeeder.model.entity.DTO.UserPrincipal;
import com.lunch.appfeeder.model.login.AppRole;
import com.lunch.appfeeder.model.login.AppUser;
import com.lunch.appfeeder.repository.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class AppUserService implements IAppUserService{
    @Autowired
    private IUserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public AppUser findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public Iterable<AppUser> findAll() {
        return userRepository.findAll();
    }

    @Override
    public AppUser saveAdmin(AppUser appUser) {
        String password = appUser.getPassword();
        String encodePassword = passwordEncoder.encode(password);//Mã hóa pass của admin
        appUser.setPassword(encodePassword);
        List<AppRole> roles = new ArrayList<>();
        roles.add(new AppRole(1L, "ROLE_ADMIN"));
        appUser.setRoles(roles);
        return userRepository.save(appUser);
    }

    @Override
    public Optional<AppUser> findById(Long id) {
        return userRepository.findById(id);
    }

    @Override
    public AppUser save(AppUser appUser) {
        String password = appUser.getPassword();
        String encodePassword = passwordEncoder.encode(password);
        appUser.setPassword(encodePassword);
        List<AppRole> roles = new ArrayList<>();
        roles.add(new AppRole(2L, "ROLE_USER"));
        appUser.setRoles(roles);
        return userRepository.save(appUser);
    }

    @Override
    public AppUser saveMerchant(AppUser appUser) {
        String password = appUser.getPassword();
        String encodePassword = passwordEncoder.encode(password);
        appUser.setPassword(encodePassword);
        List<AppRole> roles = new ArrayList<>();
        roles.add(new AppRole(3L, "ROLE_MERCHANT"));
        appUser.setRoles(roles);
        return userRepository.save(appUser);
    }
    @Override
    public void remove(Long id) {
        userRepository.deleteById(id);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        AppUser user = userRepository.findByUsername(username);
        return UserPrincipal.build(user);
    }
}
