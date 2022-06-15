package com.lunch.appfeeder.model.entity.merchant;

import javax.persistence.*;

@Entity
@Table(name = "merchant_merchantStatus")
public class Merchant_MerchantStatus {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "merchant_id")
    private Merchant merchant;

    @ManyToOne
    @JoinColumn(name = "merchantStatus_id")
    private MerchantStatus merchantStatus;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Merchant getMerchant() {
        return merchant;
    }

    public void setMerchant(Merchant merchant) {
        this.merchant = merchant;
    }

    public MerchantStatus getMerchantStatus() {
        return merchantStatus;
    }

    public void setMerchantStatus(MerchantStatus merchantStatus) {
        this.merchantStatus = merchantStatus;
    }

    public Merchant_MerchantStatus(Merchant merchant, MerchantStatus merchantStatus) {
        this.merchant = merchant;
        this.merchantStatus = merchantStatus;
    }

    public Merchant_MerchantStatus() {
    }

    public Merchant_MerchantStatus(Long id, Merchant merchant, MerchantStatus merchantStatus) {
        this.id = id;
        this.merchant = merchant;
        this.merchantStatus = merchantStatus;
    }
}
