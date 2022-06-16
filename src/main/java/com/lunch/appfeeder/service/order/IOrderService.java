package com.lunch.appfeeder.service.order;

import com.lunch.appfeeder.model.entity.Order;
import com.lunch.appfeeder.service.IGeneralService;

public interface IOrderService extends IGeneralService<Order> {
    void removeAll();
    Iterable<Order> findAllByOrderCheckFalseAndCustomer_Id (Long customerId);
    void deleteAllByOrOrderCheckFalseAndCustomer_Id (Long customerId);
}
