package com.lunch.appfeeder.model.entity;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "invoices")
public class Invoice {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String note;
    private Date date;
    @ManyToOne
    @JoinColumn(name = "customer_Id")
    private Customer customer;
    @OneToOne
    private InvoiceStatus invoiceStatus;
    @OneToMany(cascade = CascadeType.ALL)
    private List<Order> orders;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<Order> getOrders() {
        return orders;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }

    public Invoice(String note, Date date, Customer customer, InvoiceStatus invoiceStatus, List<Order> orders) {
        this.note = note;
        this.date = date;
        this.customer = customer;
        this.invoiceStatus = invoiceStatus;
        this.orders = orders;
    }

    public Invoice(Long id, String note, Date date, Customer customer, InvoiceStatus invoiceStatus, List<Order> orders) {
        this.id = id;
        this.note = note;
        this.date = date;
        this.customer = customer;
        this.invoiceStatus = invoiceStatus;
        this.orders = orders;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Invoice() {
    }

    public InvoiceStatus getInvoiceStatus() {
        return invoiceStatus;
    }

    public void setInvoiceStatus(InvoiceStatus invoiceStatus) {
        this.invoiceStatus = invoiceStatus;
    }

    public Invoice(Long id, String note, Date date, Customer customer, InvoiceStatus invoiceStatus) {
        this.id = id;
        this.note = note;
        this.date = date;
        this.customer = customer;
        this.invoiceStatus = invoiceStatus;
    }

    public Invoice(String note, Date date, Customer customer, InvoiceStatus invoiceStatus) {
        this.note = note;
        this.date = date;
        this.customer = customer;
        this.invoiceStatus = invoiceStatus;
    }
}
