package com.lunch.appfeeder.controller;

import com.lunch.appfeeder.model.entity.Customer;
import com.lunch.appfeeder.model.entity.Invoice;
import com.lunch.appfeeder.model.entity.InvoiceStatus;
import com.lunch.appfeeder.service.customer.ICustomerService;
import com.lunch.appfeeder.service.invoice.IInvoiceService;
import com.lunch.appfeeder.service.invoicestatus.IInvoiceStatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.Optional;

@RestController
@CrossOrigin("*")
@RequestMapping("/invoice")
public class InvoiceController {
    public static final long DEFAULT_STATUS = 1L;
    @Autowired
    private IInvoiceService iInvoiceService;
    @Autowired
    private ICustomerService customerService;
    @Autowired
    private IInvoiceStatusService invoiceStatusService;

    @GetMapping()
    public ResponseEntity<Iterable<Invoice>> findAllInvoice() {
        Iterable<Invoice> invoices = iInvoiceService.findAll();
        return new ResponseEntity<>(invoices, HttpStatus.OK);
    }

    @PostMapping("/{idCustomer}")
    public ResponseEntity<Invoice> createNewInvoice(@RequestBody Invoice invoice, @PathVariable Long idCustomer) {
        Optional<Customer> customer = customerService.findById(idCustomer);
        Optional<InvoiceStatus> invoiceStatus = invoiceStatusService.findById(DEFAULT_STATUS);
        Invoice invoice1 = new Invoice(invoice.getNote(),new Date(),customer.get(),invoiceStatus.get(),invoice.getOrders(),invoice.getMerchant());
//        invoice.setDate(new Date());
        return new ResponseEntity<>(iInvoiceService.save(invoice1), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Invoice> findById(@PathVariable Long id) {
        Optional<Invoice> invoice = iInvoiceService.findById(id);
        return new ResponseEntity<>(invoice.get(), HttpStatus.OK);
    }
}
