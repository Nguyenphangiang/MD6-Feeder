package com.lunch.appfeeder.model.entity;

import com.lunch.appfeeder.model.entity.merchant.Merchant;

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
    @ManyToOne
    @JoinColumn (name = "merchant_id")
    private Merchant merchant;
    private String orderAdress;


    public Merchant getMerchant() {
        return merchant;
    }

    public void setMerchant(Merchant merchant) {
        this.merchant = merchant;
    }



    public Invoice(List<Order> orders) {
        this.orders = orders;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<Order> getOrders() {
        return orders;
    }

    public String getOrderAdress() {
        return orderAdress;
    }

    public void setOrderAdress(String orderAdress) {
        this.orderAdress = orderAdress;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }

    public Invoice(String note, Date date, Customer customer, InvoiceStatus invoiceStatus, List<Order> orders, Merchant merchant, String orderAdress) {
        this.note = note;
        this.date = date;
        this.customer = customer;
        this.invoiceStatus = invoiceStatus;
        this.orders = orders;
        this.merchant = merchant;
        this.orderAdress = orderAdress;
    }

    public Invoice(Long id, String note, Date date, Customer customer, InvoiceStatus invoiceStatus, List<Order> orders, Merchant merchant, String orderAdress) {
        this.id = id;
        this.note = note;
        this.date = date;
        this.customer = customer;
        this.invoiceStatus = invoiceStatus;
        this.orders = orders;
        this.merchant = merchant;
        this.orderAdress = orderAdress;
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
