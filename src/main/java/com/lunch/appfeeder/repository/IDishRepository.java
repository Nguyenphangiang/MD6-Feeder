package com.lunch.appfeeder.repository;

import com.lunch.appfeeder.model.Dish;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IDishRepository extends CrudRepository<Dish, Long> {
}
