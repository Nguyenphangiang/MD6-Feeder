package com.lunch.appfeeder.service.cartelement;

import com.lunch.appfeeder.model.entity.CartElement;
import com.lunch.appfeeder.model.entity.Customer;
import com.lunch.appfeeder.repository.ICartElementRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;
@Service
public class CartElementService implements ICartElementService{
    @Autowired
    private ICartElementRepository cartElementRepository;
    @Override
    public Iterable<CartElement> findAll() {
        return cartElementRepository.findAll();
    }

    @Override
    public Optional<CartElement> findById(Long id) {
        return cartElementRepository.findById(id);
    }

    @Override
    public CartElement save(CartElement cartElement) {
        return cartElementRepository.save(cartElement);
    }

    @Override
    public void remove(Long id) {
        cartElementRepository.deleteById(id);
    }

    @Override
    public Iterable<CartElement> findAllByCustomer_Id(Customer customer) {
        return cartElementRepository.findAllByCustomer_Id(customer.getId());
    }

    @Override
    @Transactional
    public void deleteAllByCustomer(Customer customer) {
        cartElementRepository.deleteAllByCustomer_Id(customer.getId());
    }
}
