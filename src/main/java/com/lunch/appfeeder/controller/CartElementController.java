package com.lunch.appfeeder.controller;

import com.lunch.appfeeder.model.entity.Dish;
import com.lunch.appfeeder.model.entity.CartElement;
import com.lunch.appfeeder.model.entity.Customer;
import com.lunch.appfeeder.model.entity.Dish;
import com.lunch.appfeeder.service.cartelement.ICartElementService;
import com.lunch.appfeeder.service.customer.ICustomerService;
import com.lunch.appfeeder.service.dish.IDishService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.xml.transform.OutputKeys;
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

    @GetMapping("/customer/{idCustomer}")
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

    @GetMapping("/{idCart}")
    public ResponseEntity<CartElement> findCartElmentById(@PathVariable Long idCart) {
        Optional<CartElement> cartElement1 = cartElementService.findById(idCart);
        return new ResponseEntity<>(cartElement1.get(),HttpStatus.OK);
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

    @PutMapping("/reduce/{id}")
    public ResponseEntity<CartElement> reduceQuantityOfCartElement(@PathVariable Long id) {
        Optional<CartElement> cartElement1 = cartElementService.findById(id);
        CartElement cartElement2 = new CartElement(cartElement1.get().getCustomer(), cartElement1.get().getDish(), cartElement1.get().getQuantity()-1, cartElement1.get().getNote());
        cartElement2.setId(id);
        if (cartElement1.isPresent()) {
            cartElementService.save(cartElement2);
            return new ResponseEntity<>(cartElement2, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    @PutMapping("/increase/{id}")
    public ResponseEntity<CartElement> increaseQuantityOfCartElement(@PathVariable Long id) {
        Optional<CartElement> cartElement1 = cartElementService.findById(id);
        CartElement cartElement2 = new CartElement(cartElement1.get().getCustomer(), cartElement1.get().getDish(), cartElement1.get().getQuantity()+1, cartElement1.get().getNote());
        cartElement2.setId(id);
        if (cartElement1.isPresent()) {
            cartElementService.save(cartElement2);
            return new ResponseEntity<>(cartElement2, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
