package com.lunch.appfeeder.service.address;

import com.lunch.appfeeder.model.entity.address.OrderAddress;
import com.lunch.appfeeder.repository.IOrderAddressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class OrderAddressService implements IOrderAddressService {
    @Autowired
    private IOrderAddressRepository orderAddressRepository;
    @Override
    public Iterable<OrderAddress> findAll() {
        return orderAddressRepository.findAll();
    }

    @Override
    public Optional<OrderAddress> findById(Long id) {
        return orderAddressRepository.findById(id);
    }

    @Override
    public OrderAddress save(OrderAddress orderAddress) {
        return orderAddressRepository.save(orderAddress);
    }

    @Override
    public void remove(Long id) {
        orderAddressRepository.deleteById(id);
    }

    @Override
    public Iterable<OrderAddress> findAllByCustomer_Id(Long id) {
        return orderAddressRepository.findAllByCustomer_Id(id);
    }
}
