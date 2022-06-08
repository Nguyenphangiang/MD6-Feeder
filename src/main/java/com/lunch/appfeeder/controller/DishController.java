package com.lunch.appfeeder.controller;

import com.lunch.appfeeder.model.dish.Dish;
import com.lunch.appfeeder.model.dish.DishForm;
import com.lunch.appfeeder.service.dish.IDishService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.Optional;

@RestController
@CrossOrigin("*")
public class DishController {

    @Autowired
    private IDishService dishService;

    @Autowired
    Environment env;

    @GetMapping("/dish")
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

    @DeleteMapping("/dish/{id}")
    public ResponseEntity<Dish> deleteDish(@PathVariable Long id){
        Optional<Dish> dishOptional = dishService.findById(id);
        if (!dishOptional.isPresent()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        dishService.remove(id);
        return new ResponseEntity<>(dishOptional.get(), HttpStatus.NO_CONTENT);
    }

    @GetMapping("/dish/{id}")
    public ResponseEntity<Dish> findDishById(@PathVariable Long id){
        Optional<Dish> dishOptional = dishService.findById(id);
        if (!dishOptional.isPresent()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(dishOptional.get(), HttpStatus.OK);
    }

    @PostMapping("dish/{id}")
    public ResponseEntity<Dish> editDish(@PathVariable Long id, @ModelAttribute DishForm dishForm){
        Optional<Dish> dishOptional = dishService.findById(id);
        dishForm.setId(dishOptional.get().getId());
        MultipartFile multipartFile = dishForm.getImage();
        String fileName = multipartFile.getOriginalFilename();
        String fileUpload = env.getProperty("upload.path");
        Dish existDish = new Dish(id,fileName,dishForm.getName(),dishForm.getDescription(),dishForm.getPrice(),dishForm.getStatus(), dishForm.getMerchant_id());
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

}
