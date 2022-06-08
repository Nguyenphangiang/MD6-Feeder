package com.lunch.appfeeder.controller;

import com.lunch.appfeeder.model.Dish;
import com.lunch.appfeeder.model.DishForm;
import com.lunch.appfeeder.service.dish.IDishService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.channels.MulticastChannel;

@RestController
@CrossOrigin("*")
public class DishController {

    @Autowired
    private IDishService dishService;

    @Autowired
    Environment env;

    @GetMapping("/food")
    public ResponseEntity<Iterable<Dish>> getAllDish(){
        Iterable<Dish> dishes = dishService.findAll();
        return new ResponseEntity<>(dishes, HttpStatus.OK);
    }

    @PostMapping("/create/dish")
    public ResponseEntity<Dish> saveDish(@ModelAttribute DishForm dishForm){
        MultipartFile multipartFile = dishForm.getImage();
        String fileName = multipartFile.getOriginalFilename();
        String fileUpload = env.getProperty("upload.path");
        try {
            FileCopyUtils.copy(multipartFile.getBytes(), new File(fileUpload+fileName));
        } catch (IOException e) {
            e.printStackTrace();
        }
        Dish dish = new Dish(fileName,dishForm.getName(),dishForm.getDescription(),dishForm.getPrice(),dishForm.getStatus(), dishForm.getMerchant_id());
        dishService.save(dish);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }

}
