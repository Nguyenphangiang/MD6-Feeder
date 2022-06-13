package com.lunch.appfeeder.controller;

import com.lunch.appfeeder.model.entity.Dish;
import com.lunch.appfeeder.model.entity.DTO.DishForm;
import com.lunch.appfeeder.model.entity.DishStatus;
import com.lunch.appfeeder.model.entity.merchant.Merchant;
import com.lunch.appfeeder.service.dish.IDishService;
import com.lunch.appfeeder.service.dishStatus.IDishStatusService;
import com.lunch.appfeeder.service.merchant.IMerchantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.ModelMap;
import org.springframework.util.FileCopyUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.Optional;

@RestController
@CrossOrigin("*")
@RequestMapping("/dish")
public class DishController {

    @Autowired
    private IDishService dishService;

    @Autowired
    private IDishStatusService dishStatusService;

    @Autowired
    Environment env;

    @Autowired
    private IMerchantService merchantService;

    @GetMapping("/merchant/{merchantId}")
    public ResponseEntity<Iterable<Dish>> getAllDishByMerchant(@PathVariable Long merchantId){
        Iterable<Dish> dishes = dishService.findDishByMerchant(merchantId);
        return new ResponseEntity<>(dishes, HttpStatus.OK);
    }

    @GetMapping("")
    public ResponseEntity<Iterable<Dish>> getAllDish(){
        Iterable<Dish> dishes = dishService.findAll();
        return new ResponseEntity<>(dishes, HttpStatus.OK);
    }

    @PostMapping("/create/{merchantId}")
    public ResponseEntity<Dish> saveDish(@ModelAttribute DishForm dishForm, @PathVariable Long merchantId){
        Optional<Merchant> merchant = merchantService.findById(merchantId);
        MultipartFile multipartFile = dishForm.getImage();
        String fileName = multipartFile.getOriginalFilename();
        String fileUpload = env.getProperty("upload.path");
        try {
            FileCopyUtils.copy(multipartFile.getBytes(), new File(fileUpload+fileName));
        } catch (IOException e) {
            e.printStackTrace();
        }
        Dish dish = new Dish(fileName,dishForm.getName(),dishForm.getDescription(),dishForm.getPrice(),dishForm.getStatus(), merchant.get());
        dishService.save(dish);
        return new ResponseEntity<>(dish,HttpStatus.ACCEPTED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Dish> deleteDish(@PathVariable Long id){
        Optional<Dish> dishOptional = dishService.findById(id);
        if (!dishOptional.isPresent()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        dishService.remove(id);
        return new ResponseEntity<>(dishOptional.get(), HttpStatus.NO_CONTENT);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Dish> findDishById(@PathVariable Long id){
        Optional<Dish> dishOptional = dishService.findById(id);
        if (!dishOptional.isPresent()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(dishOptional.get(), HttpStatus.OK);
    }

    @PostMapping("/{id}/{merchantId}")
    public ResponseEntity<Dish> editDish(@PathVariable Long id, @ModelAttribute DishForm dishForm, @PathVariable Long merchantId){
        Optional<Merchant> merchant = merchantService.findById(merchantId);
        Optional<Dish> dishOptional = dishService.findById(id);
        dishForm.setId(dishOptional.get().getId());
        MultipartFile multipartFile = dishForm.getImage();
        String fileName = multipartFile.getOriginalFilename();
        String fileUpload = env.getProperty("upload.path");
        Dish existDish = new Dish(id,fileName,dishForm.getName(),dishForm.getDescription(),dishForm.getPrice(),dishForm.getStatus(), merchant.get());
        try {
            FileCopyUtils.copy(multipartFile.getBytes(), new File(fileUpload+fileName));
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (existDish.getImage().equals("filename.jpg")){
            existDish.setImage(dishOptional.get().getImage());
        }
        dishService.save(existDish);
        return new ResponseEntity<>(existDish, HttpStatus.OK);
    }

    @GetMapping("/status")
    public ResponseEntity<Iterable<DishStatus>> getAllStatus(){
        Iterable<DishStatus> dishStatuses = dishStatusService.findAll();
        return new ResponseEntity<>(dishStatuses, HttpStatus.OK);
    }
    @GetMapping("/dishName/{dishName}")
    public ResponseEntity<Iterable<Dish>> findDishByName(@PathVariable String dishName) {
        Iterable<Dish> dishes = dishService.findDishByNameContaining(dishName);
        return new ResponseEntity<>(dishes, HttpStatus.OK);
    }
}
