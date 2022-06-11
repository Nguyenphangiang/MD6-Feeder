package com.lunch.appfeeder.repository;


import com.lunch.appfeeder.model.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ICustomerRepository extends JpaRepository<Customer, Long> {
    Customer findCustomerByAppUser_Id(Long id);
}
