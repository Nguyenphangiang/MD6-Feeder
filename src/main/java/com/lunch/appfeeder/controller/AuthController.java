package com.lunch.appfeeder.controller;


import com.lunch.appfeeder.model.DTO.ChangePassword;
import com.lunch.appfeeder.model.DTO.JwtResponse;
import com.lunch.appfeeder.model.DTO.SignUpForm;

import com.lunch.appfeeder.model.entity.Customer;
import com.lunch.appfeeder.model.login.AppUser;
import com.lunch.appfeeder.repository.ICustomerRepository;

import com.lunch.appfeeder.service.customer.ICustomerService;
import com.lunch.appfeeder.service.jwt.JwtService;
import com.lunch.appfeeder.service.user.IAppUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@CrossOrigin("*")
public class AuthController {
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private IAppUserService appUserService;

    @Autowired
    private JwtService jwtService;

    @Autowired
    private ICustomerService customerService;

    @Autowired
    ApplicationEventPublisher eventPublisher;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody AppUser appUser) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(appUser.getUsername(), appUser.getPassword())
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtService.generateTokenLogin(authentication);
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        AppUser currentUser = appUserService.findByUsername(appUser.getUsername());
        return ResponseEntity.ok(new JwtResponse(jwt, currentUser.getId(), userDetails.getUsername(),userDetails.getAuthorities()));
    }
    @PostMapping("customer/register")
    public ResponseEntity<AppUser> register(@RequestBody SignUpForm user) {
        if (!user.getPassword().equals(user.getConfirmPassword())) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        AppUser newUser = new AppUser(user.getUsername(), user.getPassword());
        appUserService.save(newUser);
        Customer customer = new Customer(user.getName(),user.getEmail(),user.getPhone(),user.getAddress(),newUser);
        customerService.save(customer);
        return new ResponseEntity<>(newUser, HttpStatus.CREATED);
    }
//    @GetMapping("/admin")
//    public ResponseEntity<AppUser> admin() {
//      return new ResponseEntity<>(HttpStatus.OK);
//    }

//    @PostMapping("/changePassword/{id}")
//    public ResponseEntity<AppUser> changePassword(@PathVariable Long id, @RequestBody ChangePassword changePassword) {
//        Optional<AppUser> appUser = this.appUserService.findById(id);
//        String newPassword;
//        if (!appUser.isPresent()) {
//            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//        }
//        if (changePassword.getPassword().equals(changePassword.getConfirmPassword())) {
//            newPassword = changePassword.getPassword();
//        } else {
//            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
//        }
//        appUser.get().setPassword(newPassword);
//        return new ResponseEntity<>(appUser.get(), HttpStatus.OK);
//    }
}
