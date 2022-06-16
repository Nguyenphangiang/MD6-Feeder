package com.lunch.appfeeder.controller;

import com.lunch.appfeeder.model.entity.DTO.IMerchantSaleAmount;
import com.lunch.appfeeder.model.entity.DTO.MerchantForm;
import com.lunch.appfeeder.model.entity.DTO.MerchantWithStatus;
import com.lunch.appfeeder.model.entity.DTO.SignUpFormMerchant;
import com.lunch.appfeeder.model.entity.Dish;
import com.lunch.appfeeder.model.entity.merchant.Merchant;
import com.lunch.appfeeder.model.entity.merchant.MerchantStatus;
import com.lunch.appfeeder.model.login.AppUser;
import com.lunch.appfeeder.service.dish.IDishService;
import com.lunch.appfeeder.service.merchant.IMerchantService;
import com.lunch.appfeeder.service.user.IAppUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.Optional;


@Controller
@CrossOrigin("*")
@RequestMapping("/merchant")
public class MerchantController {
    public static final String HTTP_LOCALHOST_4200 = "http://localhost:4200";
    public static final long NOT_VERIFIED = 2L;
    @Value("${upload.path}")
    String uploadPath;
    @Autowired
    private IDishService dishService;
    @Autowired
    private IAppUserService appUserService;
    @Autowired
    private IMerchantService merchantService;

    @GetMapping()
    public ResponseEntity<Iterable<Merchant>> findAll(){
        return new ResponseEntity<>(merchantService.findAll(), HttpStatus.OK);
    }

    @PostMapping("/register")
    public ResponseEntity<AppUser> register(@ModelAttribute SignUpFormMerchant user, HttpServletRequest request) {
        if (!user.getPassword().equals(user.getConfirmPassword())) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        if (appUserService.existsAppUsersByUsername(user.getUsername())) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        AppUser newUser = new AppUser(user.getUsername(), user.getPassword());
        appUserService.saveMerchant(newUser,getSiteURL(request),user.getEmail(),user.getName());
        Merchant merchant = new Merchant();
        merchant.setAddress(user.getAddress());
        merchant.setEmail(user.getEmail());
        merchant.setName(user.getName());
        merchant.setPhone(user.getPhone());
        merchant.setUser(newUser);
        merchant.setStatus(new MerchantStatus(NOT_VERIFIED));
        merchant.setGoldPartner(false);
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
    private String getSiteURL(HttpServletRequest request) {
//        String siteURL = request.getRequestURL().toString();
        return HTTP_LOCALHOST_4200.replace(request.getServletPath(), "");
    }
    @GetMapping("/{id}")
    public ResponseEntity<Merchant> findById(@PathVariable Long id){
        return new ResponseEntity<>(merchantService.findById(id).get(), HttpStatus.OK);
    }
    @GetMapping("/username/{name}")
    public ResponseEntity<Merchant> findMerchantByUsername(@PathVariable String name) {
        Merchant merchant = merchantService.findMerchantByUserUsername(name);
        return new ResponseEntity<>(merchantService.findMerchantByUserUsername(name), HttpStatus.OK);
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
    public ResponseEntity<Merchant> saveOld(@PathVariable Long id,@ModelAttribute MerchantForm merchantForm){
        Merchant merchant = new Merchant();
        merchant.setId(id);
        merchant.setAddress(merchantForm.getAddress());
        merchant.setEmail(merchantForm.getEmail());
        merchant.setName(merchantForm.getName());
        merchant.setPhone(merchantForm.getPhone());
        merchant.setUser(merchantForm.getUser());
        merchant.setStatus(merchantForm.getStatus());
        if(merchantForm.getSafeFoodLicense()!=null){
            String licenseStringPath = merchantForm.getSafeFoodLicense().getOriginalFilename();
            try {
                FileCopyUtils.copy(merchantForm.getSafeFoodLicense().getBytes(), new File(uploadPath + licenseStringPath));
            } catch (IOException e) {
                e.printStackTrace();
            }
            merchant.setSafeFoodLicense(licenseStringPath);
        }else{
            merchant.setSafeFoodLicense(merchantService.findById(id).get().getSafeFoodLicense());
        }
        return new ResponseEntity<>(merchantService.save(merchant), HttpStatus.OK);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Merchant> delete(@PathVariable Long id){
        Optional<Merchant> merchant = merchantService.findById(id);
        merchantService.remove(id);
        appUserService.remove(merchant.get().getUser().getId());
        return new ResponseEntity<>( HttpStatus.NO_CONTENT);
    }
    @GetMapping("/userId/{userId}")
    public ResponseEntity<Merchant> findMerchantByUserId(@PathVariable Long userId) {
        Merchant merchant = merchantService.findMerchantByUser_Id(userId);
        return new ResponseEntity<>(merchant, HttpStatus.OK);
    }
    @GetMapping("/goldPartner")
    public ResponseEntity<Iterable<Merchant>> showListGoldMerchant() {
        Iterable<Merchant> merchants = merchantService.findAllByGoldPartnerTrue();
        return new ResponseEntity<>(merchants, HttpStatus.OK);
    }
    @GetMapping("/sale/amount/{year}")
    public ResponseEntity<Iterable<IMerchantSaleAmount>> showSaleAmount(@PathVariable int year) {
        Iterable<IMerchantSaleAmount> iMerchantSaleAmounts = merchantService.getMerchantSaleAmountByYear(year);
        return new ResponseEntity<>(iMerchantSaleAmounts, HttpStatus.OK);
    }
    @GetMapping("sale/amount/month/{month}")
    public ResponseEntity<Iterable<IMerchantSaleAmount>> showSaleAmountByMonth(@PathVariable int month) {
        Iterable<IMerchantSaleAmount> iMerchantSaleAmount = merchantService.getMerchantSaleAmountByMonth(month);
        return new ResponseEntity<>(iMerchantSaleAmount,HttpStatus.OK);
    }
    @GetMapping("sale/amount/quarter/{quarter}")
    public ResponseEntity<Iterable<IMerchantSaleAmount>> showSaleAmountByQuarter(@PathVariable int quarter) {
        Iterable<IMerchantSaleAmount> iMerchantSaleAmount = merchantService.getMerchantSaleAmountByQuarter(quarter);
        return new ResponseEntity<>(iMerchantSaleAmount, HttpStatus.OK);
    }
}
