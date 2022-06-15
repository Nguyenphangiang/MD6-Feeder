package com.lunch.appfeeder.repository;

import com.lunch.appfeeder.model.entity.address.OrderAddress;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IOrderAddressRepository extends JpaRepository<OrderAddress, Long> {
    Iterable<OrderAddress> findAllByCustomer_Id(Long id);
}
