package com.lunch.appfeeder.controller;

import com.lunch.appfeeder.model.dish.Dish;
import com.lunch.appfeeder.model.entity.CartElement;
import com.lunch.appfeeder.model.entity.Customer;
import com.lunch.appfeeder.service.cartelement.ICartElementService;
import com.lunch.appfeeder.service.customer.ICustomerService;
import com.lunch.appfeeder.service.dish.IDishService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Optional;

@RestController
@CrossOrigin("*")
@RequestMapping("/cart")
public class CartElementController {
    @Autowired
    private ICartElementService cartElementService;
    @Autowired
    private ICustomerService customerService;
    @Autowired
    private IDishService dishService;

    @GetMapping("/{idCustomer}")
    public ResponseEntity<Iterable<CartElement>> showAllCartElement(@PathVariable Long idCustomer) {
        Optional<Customer> customer = customerService.findById(idCustomer);
        return new ResponseEntity(cartElementService.findAllByCustomer_Id(customer.get()), HttpStatus.OK);
    }

    @PostMapping("/{idCustomer}/{idDish}")
    public ResponseEntity<CartElement> addToCartElement(@RequestBody CartElement cartElement, @PathVariable Long idCustomer, @PathVariable Long idDish) {
        Optional<Customer> customer = customerService.findById(idCustomer);
        Optional<Dish> dish = dishService.findById(idDish);
        CartElement cartElement1 = new CartElement(customer.get(), dish.get(), cartElement.getQuantity(), cartElement.getNote());
        return new ResponseEntity<>(cartElementService.save(cartElement1), HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<CartElement> deleteCartElement(@PathVariable Long id) {
        Optional<CartElement> cartElement = cartElementService.findById(id);
        if (cartElement.isPresent()) {
            cartElementService.remove(id);
            return new ResponseEntity<>(cartElement.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/all/{idCustomer}")
    public ResponseEntity<CartElement> deleteAllCartElementByCustomerId(@PathVariable Long idCustomer) {
        Optional<Customer> customer = customerService.findById(idCustomer);
        if (customer.isPresent()){
            cartElementService.deleteAllByCustomer(customer.get());
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<CartElement> updateCartElement(@PathVariable Long id, @RequestBody CartElement cartElement) {
        Optional<CartElement> cartElement1 = cartElementService.findById(id);
        cartElement.setId(id);
        if (cartElement1.isPresent()) {
            cartElementService.save(cartElement);
            return new ResponseEntity<>(cartElement1.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
