package com.lunch.appfeeder.service.invoicestatus;

import com.lunch.appfeeder.model.entity.InvoiceStatus;
import com.lunch.appfeeder.repository.IInvoiceStatusRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class InvoiceStatusService implements IInvoiceStatusService {
    @Autowired
    private IInvoiceStatusRepository orderStatusRepository;

    public Iterable<InvoiceStatus> findAll() {
        return orderStatusRepository.findAll();
    }

    @Override
    public Optional<InvoiceStatus> findById(Long id) {
        return Optional.empty();
    }

    @Override
    public InvoiceStatus save(InvoiceStatus orderStatus) {
        return null;
    }

    @Override
    public void remove(Long id) {

    }
}
