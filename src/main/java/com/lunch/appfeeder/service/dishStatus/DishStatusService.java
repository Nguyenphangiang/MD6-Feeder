package com.lunch.appfeeder.service.dishStatus;

import com.lunch.appfeeder.model.entity.DishStatus;
import com.lunch.appfeeder.repository.IDishStatusRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
@Service
public class DishStatusService implements IDishStatusService{

    @Autowired
    private IDishStatusRepository dishStatusRepository;

    @Override
    public Iterable<DishStatus> findAll() {
        return dishStatusRepository.findAll();
    }

    @Override
    public Optional<DishStatus> findById(Long id) {
        return dishStatusRepository.findById(id);
    }

    @Override
    public DishStatus save(DishStatus dishStatus) {
        return dishStatusRepository.save(dishStatus);
    }

    @Override
    public void remove(Long id) {
        dishStatusRepository.deleteById(id);
    }
}
