package com.lunch.appfeeder.service.order;

import com.lunch.appfeeder.model.entity.Order;
import com.lunch.appfeeder.repository.orderRepository.IOrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
public class OrderService implements IOrderService{
    @Autowired
    IOrderRepository orderRepository;

    @Override
    public Iterable<Order> findAll() {
        return orderRepository.findAll();
    }

    @Override
    public Optional<Order> findById(Long id) {
        return orderRepository.findById(id);
    }

    @Override
    public Order save(Order order) {
        return orderRepository.save(order);
    }

    @Override
    public void remove(Long id) {
        orderRepository.deleteById(id);
    }

    @Override
    public Iterable<Order> findAllByOrderCheckFalseAndCustomer_Id(Long customerId) {
        return orderRepository.findAllByOrderCheckFalseAndCustomer_Id(customerId);
    }

    @Override
    public void removeAll(){
        orderRepository.deleteAll();
    }

    @Override
    @Transactional
    public void deleteAllByOrOrderCheckFalseAndCustomer_Id(Long customerId) {
        orderRepository.deleteAllByOrOrderCheckFalseAndCustomer_Id(customerId);
    }
}
