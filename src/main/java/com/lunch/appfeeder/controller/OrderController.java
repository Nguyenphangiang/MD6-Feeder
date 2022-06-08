package com.lunch.appfeeder.controller;

import com.lunch.appfeeder.model.order.Order;
import com.lunch.appfeeder.service.order.IOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin
@RequestMapping("/order")
public class OrderController {
    @Autowired
    private IOrderService orderService;

    @GetMapping
    public ResponseEntity<Iterable<Order>> findAllOrder(){
        Iterable<Order> orders = orderService.findAll();
        List<Order> orderList = (List<Order>) orders;
//        if (orderList.isEmpty()){
//            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
//        }
        return new ResponseEntity<>(orderList, HttpStatus.OK);
    }
    @GetMapping("/{id}")
    public  ResponseEntity<Optional<Order>> findOrderById(@PathVariable Long id){
        Optional<Order> order = orderService.findById(id);
//        if (!order.isPresent()){
//            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//        }
        return new ResponseEntity<>(order, HttpStatus.OK);
    }
    @PostMapping("/create")
    public ResponseEntity<Order> createNewOrder(@RequestBody Order order){
        orderService.save(order);
        return new ResponseEntity<>(order, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Order> updateOrderInfo(@PathVariable Long id, @RequestBody Order order){
        Optional<Order> originalOrder = orderService.findById(id);
        if (!originalOrder.isPresent()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        order.setId(originalOrder.get().getId());
        orderService.save(order);
        return new ResponseEntity<>(order, HttpStatus.OK);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Order> removeOrder(@PathVariable Long id){
        Optional<Order> order = orderService.findById(id);
        if (!order.isPresent()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        orderService.remove(id);
        return new ResponseEntity<>(order.get(), HttpStatus.OK);
    }
    @DeleteMapping
    public ResponseEntity<Iterable<Order>> removeAllOrder(){
        Iterable<Order> orders = orderService.findAll();
        orderService.removeAll();
        return new ResponseEntity<>(orders, HttpStatus.OK);
    }

}
