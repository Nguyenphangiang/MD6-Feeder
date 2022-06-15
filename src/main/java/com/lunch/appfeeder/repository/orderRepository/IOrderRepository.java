package com.lunch.appfeeder.repository.orderRepository;

import com.lunch.appfeeder.model.entity.Order;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IOrderRepository extends CrudRepository<Order, Long> {
    Iterable<Order> findAllByDish_Merchant_Id(Long idMerchant);
}
