package com.lunch.appfeeder.controller;

import com.lunch.appfeeder.model.entity.Customer;
import com.lunch.appfeeder.model.entity.address.OrderAddress;
import com.lunch.appfeeder.service.address.IOrderAddressService;
import com.lunch.appfeeder.service.customer.ICustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin("*")
@RequestMapping("/orderAddress")
public class OrderAddressController {
    @Autowired
    private IOrderAddressService orderAddressService;
    @Autowired
    private ICustomerService customerService;

    @GetMapping("{idCustomer}")
    public ResponseEntity<Iterable<OrderAddress>> showAllOrderAddress(@PathVariable Long idCustomer) {
        return new ResponseEntity<>(orderAddressService.findAllByCustomer_Id(idCustomer), HttpStatus.OK);
    }
    @PostMapping("{idCustomer}")
    public ResponseEntity<OrderAddress> addNewOrderAddress(@RequestBody OrderAddress orderAddress, @PathVariable Long idCustomer) {
        Optional<Customer> customer = customerService.findById(idCustomer);
        OrderAddress newOrderAddress = new OrderAddress(
                orderAddress.getType(), orderAddress.getName(), customer.get()
        );
        orderAddressService.save(newOrderAddress);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
    @PutMapping("{id}/{idCustomer}")
    public ResponseEntity<OrderAddress> updateOrderAddress(@RequestBody OrderAddress orderAddress,@PathVariable Long id, @PathVariable Long idCustomer) {
        Optional<Customer> customer = customerService.findById(idCustomer);
        OrderAddress oldOrderAddress = new OrderAddress(
                id, orderAddress.getType(), orderAddress.getName(), customer.get()
        );
        orderAddressService.save(oldOrderAddress);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    @DeleteMapping("{id}")
    public ResponseEntity<OrderAddress> removeOrderAddress(@PathVariable Long id) {
        orderAddressService.remove(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
