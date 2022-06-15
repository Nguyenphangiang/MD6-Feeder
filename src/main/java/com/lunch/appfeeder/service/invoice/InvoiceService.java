package com.lunch.appfeeder.service.invoice;

import com.lunch.appfeeder.model.entity.Invoice;
import com.lunch.appfeeder.repository.IInvoiceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class InvoiceService implements IInvoiceService {
    @Autowired
    private IInvoiceRepository iInvoiceRepository;
    @Override
    public Iterable<Invoice> findAll() {
        return iInvoiceRepository.findAll();
    }

    @Override
    public Optional<Invoice> findById(Long id) {
        return iInvoiceRepository.findById(id);
    }

    @Override
    public Invoice save(Invoice invoice) {
        return iInvoiceRepository.save( invoice);
    }

    @Override
    public void remove(Long id) {
        iInvoiceRepository.deleteById(id);
    }

    @Override
    public Iterable<Invoice> findAllByCustomer_Id(Long idCustomer) {
        return iInvoiceRepository.findAllByCustomer_Id(idCustomer);
    }
}
