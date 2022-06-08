package com.lunch.appfeeder.repository;

import com.lunch.appfeeder.model.entity.CartElement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ICartElementRepository extends JpaRepository<CartElement,Long> {
}
