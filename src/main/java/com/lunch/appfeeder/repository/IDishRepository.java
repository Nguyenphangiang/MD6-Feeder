package com.lunch.appfeeder.repository;

import com.lunch.appfeeder.model.entity.Dish;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IDishRepository extends PagingAndSortingRepository<Dish, Long> {
    Iterable<Dish> findAllByMerchant_Id(Long merchantId);

    Iterable<Dish> findDishByNameContaining(String name);

    Iterable<Dish> findAllByRecommendTrue();

    Iterable<Dish> findAllByDishStatusId(Long id);

}
