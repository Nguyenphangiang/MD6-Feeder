package com.lunch.appfeeder.repository.orderRepository;

import com.lunch.appfeeder.model.entity.Order;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IOrderRepository extends CrudRepository<Order, Long> {
    Iterable<Order> findAllByDish_Merchant_Id(Long idMerchant);
    Iterable<Order> findAllByOrderCheckFalseAndCustomer_Id (Long idCustomer);
    void deleteAllByOrOrderCheckFalseAndCustomer_Id(Long idCustomer);
}
