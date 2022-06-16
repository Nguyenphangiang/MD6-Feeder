package com.lunch.appfeeder.controller;

import com.lunch.appfeeder.model.entity.Customer;
import com.lunch.appfeeder.model.entity.Invoice;
import com.lunch.appfeeder.model.entity.InvoiceStatus;
import com.lunch.appfeeder.service.customer.ICustomerService;
import com.lunch.appfeeder.service.invoice.IInvoiceService;
import com.lunch.appfeeder.service.invoicestatus.IInvoiceStatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
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
    @GetMapping("/merchant/{id}")
    public ResponseEntity<Iterable<Invoice>> findAllByMerchantId(@PathVariable Long id) {
        Iterable<Invoice> invoices = iInvoiceService.findAllByMerchant_Id(id);
        return new ResponseEntity<>(invoices, HttpStatus.OK);
    }
    @PutMapping("/{id}")
    public ResponseEntity<Invoice> updateInvoice(@PathVariable Long id,@RequestBody Invoice invoice) {
        invoice.setId(id);
        return new ResponseEntity<>(iInvoiceService.save(invoice), HttpStatus.OK);
    }
    @GetMapping("/customer-name")
    public ResponseEntity<Iterable<Invoice>> findAllByCustomerName(@Param("name") String name) {
        Iterable<Invoice> invoices = iInvoiceService.findAllByCustomer_Name("%"+name+"%");
        return new ResponseEntity<>(invoices, HttpStatus.OK);
    }
    @GetMapping("/customer-phone")
    public ResponseEntity<Iterable<Invoice>> findAllByCustomerPhone(@Param("phone") String phone) {
        Iterable<Invoice> invoices = iInvoiceService.findAllByCustomer_Phone("%"+phone+"%");
        return new ResponseEntity<>(invoices, HttpStatus.OK);
    }
    @GetMapping("/customerInfo")
    public ResponseEntity<Iterable<Invoice>> findAllByCustomerNameOrPhone(@Param("input") String input) {
        Iterable<Invoice> invoices = iInvoiceService.findAllByCustomer_NameOrPhone("%"+input+"%");
        return new ResponseEntity<>(invoices, HttpStatus.OK);
    }
}
