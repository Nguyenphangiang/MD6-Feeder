package com.lunch.appfeeder.repository;

import com.lunch.appfeeder.model.entity.CartElement;
import com.lunch.appfeeder.model.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ICartElementRepository extends JpaRepository<CartElement, Long> {
    Iterable<CartElement> findAllByCustomer_Id(Long idCustomer);
    void deleteAllByCustomer_Id (Long idCustomer);
}
