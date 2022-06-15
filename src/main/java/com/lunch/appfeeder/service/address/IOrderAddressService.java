package com.lunch.appfeeder.service.address;

import com.lunch.appfeeder.model.entity.address.OrderAddress;
import com.lunch.appfeeder.service.IGeneralService;

public interface IOrderAddressService extends IGeneralService<OrderAddress> {
    Iterable<OrderAddress> findAllByCustomer_Id(Long id);
}
