package com.lunch.appfeeder.controller;

import com.lunch.appfeeder.model.entity.CartElement;
import com.lunch.appfeeder.model.entity.Order;
import com.lunch.appfeeder.service.cartelement.ICartElementService;
import com.lunch.appfeeder.service.invoice.IInvoiceService;
import com.lunch.appfeeder.service.order.IOrderService;
import com.lunch.appfeeder.service.invoicestatus.IInvoiceStatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin("*")
@RequestMapping("/order")
public class OrderController {
    @Autowired
    private IOrderService orderService;
    @Autowired
    private ICartElementService cartElementService;
    @Autowired
    private IInvoiceService iInvoiceService;
    @Autowired
    private IInvoiceStatusService invoiceStatusService;


    @GetMapping
    public ResponseEntity<Iterable<Order>> findAllOrder() {
        Iterable<Order> orders = orderService.findAll();
        List<Order> orderList = (List<Order>) orders;
        if (orderList.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(orderList, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<Order>> findOrderById(@PathVariable Long id) {
        Optional<Order> order = orderService.findById(id);
        if (!order.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(order, HttpStatus.OK);
    }

//    @GetMapping("/customer/{id}")
//    public ResponseEntity<Iterable<Order>> findOrderByUserId(@PathVariable Long id){
//        Iterable<Order> allOrder = orderService.findAll();
//        List<Order> allOrders = (List<Order>) allOrder;
//        List<Order> orderList = new ArrayList<>();
//        for (Order order : allOrders){
//            if (order.getCustomer().getId().equals(id)){
//                orderList.add(order);
//            }
//        }
//        return new ResponseEntity<>(orderList, HttpStatus.OK);
//    }

    @GetMapping("/merchant/{id}")
    public ResponseEntity<Iterable<Order>> findOrderByMerchantId(@PathVariable Long id) {
        Iterable<Order> allOrder = orderService.findAll();
        List<Order> allOrders = (List<Order>) allOrder;
        List<Order> orderList = new ArrayList<>();
        for (Order order : allOrders) {
            if (order.getDish().getMerchant().getId().equals(id)) {
                orderList.add(order);
            }
        }
        return new ResponseEntity<>(orderList, HttpStatus.OK);
    }


    @GetMapping("/ordercheck/{customerId}")
    public ResponseEntity<Iterable<Order>> findAllByCustomerIdAndCheckFalse(@PathVariable Long customerId) {
        return new ResponseEntity<>(orderService.findAllByOrderCheckFalseAndCustomer_Id(customerId), HttpStatus.OK);
    }

    @PostMapping()
    public ResponseEntity<Order> createNewOrder(@RequestBody Order order) {
        order.setOrderCheck(false);
        orderService.save(order);
        return new ResponseEntity<>(order, HttpStatus.CREATED);
    }

    @PutMapping("/setstatus/{id}")
    public ResponseEntity<Order> updateOrderInfo(@PathVariable Long id, @RequestBody Order order) {
        Optional<Order> originalOrder = orderService.findById(id);
        if (!originalOrder.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        order.setId(originalOrder.get().getId());
        order.setOrderCheck(true);
        orderService.save(order);
        return new ResponseEntity<>(order, HttpStatus.OK);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Order> removeOrder(@PathVariable Long id) {
        Optional<Order> order = orderService.findById(id);
        if (!order.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        orderService.remove(id);
        return new ResponseEntity<>(order.get(), HttpStatus.OK);
    }

    @DeleteMapping
    public ResponseEntity<Iterable<Order>> removeAllOrder() {
        Iterable<Order> orders = orderService.findAll();
        orderService.removeAll();
        return new ResponseEntity<>(orders, HttpStatus.OK);
    }
    @PutMapping("/reduce/{id}")
    public ResponseEntity<Order> reduceQuantityOfOrderElement(@PathVariable Long id) {
        Optional<Order> order = orderService.findById(id);
        Order order1 = new Order(order.get().getDish(),order.get().getQuantity()-1,order.get().isOrderCheck(),order.get().getCustomer());
        order1.setId(id);
        if (order.isPresent()) {
            orderService.save(order1);
            return new ResponseEntity<>(order1, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    @PutMapping("/increase/{id}")
    public ResponseEntity<CartElement> increaseQuantityOfCartElement(@PathVariable Long id) {
        Optional<Order> order = orderService.findById(id);
        Order order1 = new Order(order.get().getDish(),order.get().getQuantity()+1,order.get().isOrderCheck(),order.get().getCustomer());
        order1.setId(id);
        if (order.isPresent()) {
            orderService.save(order1);
            return new ResponseEntity<>( HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
