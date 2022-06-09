package com.lunch.appfeeder.service.cartelement;

import com.lunch.appfeeder.model.entity.CartElement;
import com.lunch.appfeeder.model.entity.Customer;
import com.lunch.appfeeder.service.IGeneralService;

public interface ICartElementService extends IGeneralService<CartElement> {
 Iterable<CartElement> findAllByCustomer_Id(Customer customer);
 void deleteAllByCustomer(Customer customer);
}
