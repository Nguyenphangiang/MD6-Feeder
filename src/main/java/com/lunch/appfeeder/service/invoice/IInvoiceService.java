package com.lunch.appfeeder.service.invoice;

import com.lunch.appfeeder.model.entity.Invoice;
import com.lunch.appfeeder.service.IGeneralService;

public interface IInvoiceService extends IGeneralService<Invoice> {
    Iterable<Invoice> findAllByCustomer_Id(Long idCustomer);
}
