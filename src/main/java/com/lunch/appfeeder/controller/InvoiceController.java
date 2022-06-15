package com.lunch.appfeeder.controller;

import com.lunch.appfeeder.model.entity.Invoice;
import com.lunch.appfeeder.service.invoice.IInvoiceService;
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
    @Autowired
    private IInvoiceService iInvoiceService;
    @GetMapping()
    public ResponseEntity<Iterable<Invoice>> findAllInvoice (){
        Iterable<Invoice> invoices = iInvoiceService.findAll();
        return new ResponseEntity<>(invoices, HttpStatus.OK);
    }
    @PostMapping()
    public ResponseEntity<Invoice> createNewInvoice(@RequestBody Invoice invoice){
        invoice.setDate(new Date());
        return new ResponseEntity<>(iInvoiceService.save(invoice),HttpStatus.CREATED);
    }
    @GetMapping("/{id}")
    public ResponseEntity<Invoice> findById (@PathVariable Long id){
        Optional<Invoice> invoice = iInvoiceService.findById(id);
        return new ResponseEntity<>(invoice.get(),HttpStatus.OK);
    }
}
