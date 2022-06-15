package com.lunch.appfeeder.controller;

import com.lunch.appfeeder.model.entity.Customer;
import com.lunch.appfeeder.model.entity.Dish;
import com.lunch.appfeeder.model.entity.merchant.Merchant;
import com.lunch.appfeeder.model.entity.merchant.MerchantStatus;
import com.lunch.appfeeder.service.customer.ICustomerService;
import com.lunch.appfeeder.service.dish.IDishService;
import com.lunch.appfeeder.service.merchant.IMerchantService;
import com.lunch.appfeeder.service.user.IAppUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/admin")
@CrossOrigin("*")
public class AdminController {
    public static final long VERIFIED = 1L;
    public static final long BLOCKED = 3L;
    public static final long STATUS_SOLD = 2L;
    @Autowired
    private ICustomerService customerService;
    @Autowired
    private IAppUserService appUserService;

    @Autowired
    private IMerchantService merchantService;

    @Autowired
    private IDishService dishService;

    @GetMapping("customer/list")
    public ResponseEntity<Iterable<Customer>> showAllCustomer() {
        return new ResponseEntity<>(customerService.findAll(), HttpStatus.OK);
    }
    @GetMapping("merchant/list")
    public ResponseEntity<Iterable<Merchant>> findAll(){
        return new ResponseEntity<>(merchantService.findAll(), HttpStatus.OK);
    }
    @DeleteMapping("customer/delete/{id}")
    public ResponseEntity<Customer> deleteCustomer(@PathVariable Long id) {
        Optional<Customer> customer = customerService.findById(id);
        customerService.remove(id);
        appUserService.remove(customer.get().getAppUser().getId());
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    @GetMapping("merchant/active/{id}")
    public ResponseEntity<Merchant> activeMerchantById(@PathVariable Long id) {
        Merchant oldMerchant = merchantService.findById(id).get();
        oldMerchant.setStatus(new MerchantStatus(VERIFIED));
        merchantService.save(oldMerchant);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    @GetMapping("merchant/block/{id}")
    public ResponseEntity<Merchant> blockMerchantById(@PathVariable Long id) {
        Merchant oldMerchant = merchantService.findById(id).get();
        oldMerchant.setStatus(new MerchantStatus(BLOCKED));
        merchantService.save(oldMerchant);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    @DeleteMapping("merchant/{id}")
    public ResponseEntity<Merchant> delete(@PathVariable Long id){
        Optional<Merchant> merchant = merchantService.findById(id);
        merchantService.remove(id);
        appUserService.remove(merchant.get().getUser().getId());
        return new ResponseEntity<>( HttpStatus.NO_CONTENT);
    }
    @GetMapping("merchant/goldPartner")
    public ResponseEntity<Iterable<Merchant>> showListGoldMerchant() {
        Iterable<Merchant> merchants = merchantService.findAllByGoldPartnerTrue();
        return new ResponseEntity<>(merchants, HttpStatus.OK);
    }
    @GetMapping("merchant/setGold/{id}")
    public ResponseEntity<Merchant> setGoldPartnerMerchant(@PathVariable Long id) {
        Merchant oldMerchant = merchantService.findById(id).get();
        oldMerchant.setGoldPartner(!oldMerchant.isGoldPartner());
        merchantService.save(oldMerchant);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    @GetMapping("dish/recommend/sold")
    public ResponseEntity<Iterable<Dish>> showListSoldDish() {
        Iterable<Dish> dishes = dishService.findAllByDishStatusId(STATUS_SOLD);
        return new ResponseEntity<>(dishes, HttpStatus.OK);
    }
    @GetMapping("merchant/restaurant/list")
    public ResponseEntity<Iterable<Merchant>> showAllRestaurant() {
        Iterable<Merchant> merchants = merchantService.findAll();
        return new ResponseEntity<>( merchants,HttpStatus.OK);
    }
    @GetMapping("dish/recommend/{idDish}")
    public ResponseEntity<Dish> addDishRecommend(@PathVariable Long idDish) {
        Dish oldDish = dishService.findById(idDish).get();
        oldDish.setRecommend(!oldDish.isRecommend());
        dishService.save(oldDish);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
