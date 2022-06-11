package com.lunch.appfeeder.controller;


import com.lunch.appfeeder.model.entity.DTO.JwtResponse;

import com.lunch.appfeeder.model.entity.Customer;
import com.lunch.appfeeder.model.entity.DTO.SignUpFormCustomer;
import com.lunch.appfeeder.model.entity.merchant.Merchant;
import com.lunch.appfeeder.model.login.AppUser;
import com.lunch.appfeeder.repository.ICustomerRepository;

import com.lunch.appfeeder.service.customer.ICustomerService;
import com.lunch.appfeeder.service.jwt.JwtService;
import com.lunch.appfeeder.service.merchant.IMerchantService;
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
    private IMerchantService merchantService;

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
    public ResponseEntity<AppUser> register(@ModelAttribute SignUpFormCustomer user) {
        if (!user.getPassword().equals(user.getConfirmPassword())) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        AppUser newUser = new AppUser(user.getUsername(), user.getPassword());
        appUserService.save(newUser);
        Customer customer = new Customer(user.getName(),user.getEmail(),user.getPhone(),user.getAddress(),newUser);
        customerService.save(customer);
        return new ResponseEntity<>(newUser, HttpStatus.CREATED);
    }
    @PutMapping("customer/update/{id}")
    public ResponseEntity<Customer> updateCustomerInformation(@RequestBody SignUpFormCustomer customer, @PathVariable Long id) {
        Customer oldCustomer = customerService.findCustomerByAppUser_Id(id);
        Customer newCustomer = new Customer(oldCustomer.getId(), customer.getName(),customer.getEmail(),customer.getPhone(),customer.getAddress(),oldCustomer.getAppUser());
        customerService.save(newCustomer);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    @GetMapping("customer/detail/{id}")
    public ResponseEntity<Customer> showCustomerDetail(@PathVariable Long id) {
        return new ResponseEntity<>(customerService.findCustomerByAppUser_Id(id), HttpStatus.OK);
    }
    @GetMapping("customer/findMerchant/{name}")
    public ResponseEntity<Iterable<Merchant>> findMerchantByName(@PathVariable String name) {
        Iterable<Merchant> merchants = merchantService.findMerchantByNameContaining(name);
        return new ResponseEntity<>(merchantService.findMerchantByNameContaining(name), HttpStatus.OK);
    }
    @GetMapping("customer/list")
    public ResponseEntity<Iterable<Customer>> showAllCustomer() {
        return new ResponseEntity<>(customerService.findAll(), HttpStatus.OK);
    }
    @DeleteMapping("customer/delete/{id}")
    public ResponseEntity<Customer> deleteCustomer(@PathVariable Long id) {
        Optional<Customer> customer = customerService.findById(id);
        customerService.remove(id);
        appUserService.remove(customer.get().getAppUser().getId());
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
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
