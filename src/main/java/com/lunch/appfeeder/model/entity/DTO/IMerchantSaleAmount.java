package com.lunch.appfeeder.model.entity.DTO;

import com.lunch.appfeeder.model.entity.Customer;
import com.lunch.appfeeder.model.entity.Dish;
import com.lunch.appfeeder.model.entity.Invoice;

import java.util.Date;

public interface IMerchantSaleAmount {
    Date getDate();
    Long getCustomer();
    Long getInvoice();
    Long getDish();
    String getTotal();
}
