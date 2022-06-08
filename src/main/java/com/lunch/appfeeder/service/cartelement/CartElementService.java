package com.lunch.appfeeder.service.cartelement;

import com.lunch.appfeeder.model.entity.CartElement;
import com.lunch.appfeeder.repository.ICartElementRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
}
