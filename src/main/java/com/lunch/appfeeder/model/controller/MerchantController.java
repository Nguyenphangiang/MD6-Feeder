package com.lunch.appfeeder.model.controller;

import com.lunch.appfeeder.model.entity.DTO.MerchantWithStatus;
import com.lunch.appfeeder.model.entity.DTO.SignUpFormMerchant;
import com.lunch.appfeeder.model.entity.merchant.Merchant;
import com.lunch.appfeeder.model.entity.merchant.MerchantStatus;
import com.lunch.appfeeder.model.login.AppUser;
import com.lunch.appfeeder.service.merchant.IMerchantService;
import com.lunch.appfeeder.service.user.IAppUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.io.IOException;


@Controller
@RequestMapping("/merchant")
public class MerchantController {

    @Value("${upload.path}")
    String uploadPath;

    @Autowired
    private IAppUserService appUserService;
    @Autowired
    private IMerchantService merchantService;

    @GetMapping()
    public ResponseEntity<Iterable<Merchant>> findAll(){
        return new ResponseEntity<>(merchantService.findAll(), HttpStatus.OK);
    }

    @PostMapping("/register")
    public ResponseEntity<AppUser> register(@ModelAttribute SignUpFormMerchant user) {
        if (!user.getPassword().equals(user.getConfirmPassword())) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        AppUser newUser = new AppUser(user.getUsername(), user.getPassword());
        appUserService.saveMerchant(newUser);
        Merchant merchant = new Merchant();
        merchant.setAddress(user.getAddress());
        merchant.setEmail(user.getEmail());
        merchant.setName(user.getName());
        merchant.setPhone(user.getPhone());
        merchant.setUser(newUser);
        merchant.setStatus(new MerchantStatus(2L));

        String licenseStringPath = user.getSafeFoodLicense().getOriginalFilename();
        try {
            FileCopyUtils.copy(user.getSafeFoodLicense().getBytes(), new File(uploadPath + licenseStringPath));
        } catch (IOException e) {
            e.printStackTrace();
        }
        merchant.setSafeFoodLicense(licenseStringPath);
        merchantService.save(merchant);
        return new ResponseEntity<>(newUser, HttpStatus.CREATED);
    }
    @GetMapping("/{id}")
    public ResponseEntity<Merchant> findById(@PathVariable Long id){
        return new ResponseEntity<>(merchantService.findById(id).get(), HttpStatus.OK);
    }
//    @PostMapping()
//    public ResponseEntity<Merchant> saveNew(@ModelAttribute MerchantWithStatus merchantWithStatus){
//        Merchant merchant = new Merchant();
//        merchant.setAddress(merchantWithStatus.getAddress());
//        merchant.setEmail(merchantWithStatus.getEmail());
//        merchant.setName(merchantWithStatus.getName());
//        merchant.setPhone(merchantWithStatus.getPhone());
//        merchant.setSafeFoodLicense(merchantWithStatus.getSafeFoodLicense());
//        merchant.setUser(merchantWithStatus.getUser());
//        MerchantStatus ms = new MerchantStatus();
//        ms.setId(2L);
//        return new ResponseEntity<>(merchantService.save(merchant,ms), HttpStatus.CREATED);
//    }
    @PostMapping("/{id}")
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
    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable Long id){
        merchantService.remove(id);
        return new ResponseEntity<>( HttpStatus.NO_CONTENT);
    }
}
