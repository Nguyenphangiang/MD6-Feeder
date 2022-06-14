package com.lunch.appfeeder.service.dish;

import com.lunch.appfeeder.model.entity.Dish;
import com.lunch.appfeeder.repository.IDishRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
@Service
public class DishService implements IDishService{

    @Autowired
    private IDishRepository dishRepository;

    @Override
    public Iterable<Dish> findAll() {
        return dishRepository.findAll();
    }

    @Override
    public Optional<Dish> findById(Long id) {
        return dishRepository.findById(id);
    }

    @Override
    public Dish save(Dish dish) {
        return dishRepository.save(dish);
    }

    @Override
    public void remove(Long id) {
        dishRepository.deleteById(id);
    }

    @Override
    public Iterable<Dish> findDishByMerchant(Long merchantId) {
        return dishRepository.findAllByMerchant_Id(merchantId);
    }

    @Override
    public Iterable<Dish> findDishByNameContaining(String name) {
        return dishRepository.findDishByNameContaining(name);
    }

}
