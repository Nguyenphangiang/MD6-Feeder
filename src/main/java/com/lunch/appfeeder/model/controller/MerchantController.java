package com.lunch.appfeeder.model.controller;

import com.lunch.appfeeder.model.entity.DTO.MerchantWithStatus;
import com.lunch.appfeeder.model.entity.merchant.Merchant;
import com.lunch.appfeeder.service.merchant.IMerchantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


@Controller
@RequestMapping("/merchant")
public class MerchantController {
    @Autowired
    private IMerchantService merchantService;

    @GetMapping()
    public ResponseEntity<Iterable<Merchant>> findAll(){
        return new ResponseEntity<>(merchantService.findAll(), HttpStatus.OK);
    }
    @GetMapping("/id")
    public ResponseEntity<Merchant> findById(@PathVariable Long id){
        return new ResponseEntity<>(merchantService.findById(id).get(), HttpStatus.OK);
    }
    @PostMapping()
    public ResponseEntity<Merchant> saveNew(@ModelAttribute MerchantWithStatus merchantWithStatus){
        Merchant merchant = new Merchant();
        merchant.setAddress(merchantWithStatus.getAddress());
        merchant.setEmail(merchantWithStatus.getEmail());
        merchant.setName(merchantWithStatus.getName());
        merchant.setPhone(merchantWithStatus.getPhone());
        merchant.setSafeFoodLicense(merchantWithStatus.getSafeFoodLicense());
        merchant.setUser(merchantWithStatus.getUser());
        return new ResponseEntity<>(merchantService.save(merchant), HttpStatus.CREATED);
    }
    @PostMapping("/id")
    public ResponseEntity<Merchant> saveOld(@PathVariable Long id,@ModelAttribute MerchantWithStatus merchantWithStatus){
        Merchant merchant = new Merchant();
        merchant.setId(id);
        merchant.setAddress(merchantWithStatus.getAddress());
        merchant.setEmail(merchantWithStatus.getEmail());
        merchant.setName(merchantWithStatus.getName());
        merchant.setPhone(merchantWithStatus.getPhone());
        merchant.setSafeFoodLicense(merchantWithStatus.getSafeFoodLicense());
        merchant.setUser(merchantWithStatus.getUser());
        return new ResponseEntity<>(merchantService.save(merchant), HttpStatus.OK);
    }
    @DeleteMapping("/id")
    public ResponseEntity delete(@PathVariable Long id){
        merchantService.remove(id);
        return new ResponseEntity<>( HttpStatus.NO_CONTENT);
    }
}
