package com.lunch.appfeeder.controller;

import com.lunch.appfeeder.model.entity.CartElement;
import com.lunch.appfeeder.service.cartelement.ICartElementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@CrossOrigin("*")
@RequestMapping("/cart")
public class CartElementController {
    @Autowired
    private ICartElementService cartElementService;

    @GetMapping
    public ResponseEntity<Iterable<CartElement>> showAllCartElement() {
        return new ResponseEntity(cartElementService.findAll(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<CartElement> addToCartElement(@RequestBody CartElement cartElement) {
        return new ResponseEntity<>(cartElementService.save(cartElement), HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<CartElement> deleteCartElement(@PathVariable Long id) {
        Optional<CartElement> cartElement = cartElementService.findById(id);
        if (cartElement.isPresent()) {
            cartElementService.remove(id);
            return new ResponseEntity<>(cartElement.get(), HttpStatus.OK);
        }else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    @PutMapping("/{id}")
    public ResponseEntity<CartElement> updateCartElement(@PathVariable Long id , @RequestBody CartElement cartElement){
        Optional<CartElement> cartElement1 = cartElementService.findById(id);
        cartElement.setId(id);
        if (cartElement1.isPresent()){
            cartElementService.save(cartElement);
            return new ResponseEntity<>(cartElement1.get(),HttpStatus.OK);
        }else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
