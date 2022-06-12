package com.lunch.appfeeder.repository;

import com.lunch.appfeeder.model.entity.DishStatus;
import org.springframework.data.repository.CrudRepository;

public interface IDishStatusRepository extends CrudRepository<DishStatus, Long> {
}
