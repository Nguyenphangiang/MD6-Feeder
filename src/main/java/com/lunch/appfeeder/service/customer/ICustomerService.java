package com.lunch.appfeeder.service.customer;


import com.lunch.appfeeder.model.entity.Customer;
import com.lunch.appfeeder.service.IGeneralService;

public interface ICustomerService extends IGeneralService<Customer> {
    Customer findCustomerByAppUser_Id(Long id);
}
