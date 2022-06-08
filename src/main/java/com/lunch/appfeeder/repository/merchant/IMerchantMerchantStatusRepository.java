package com.lunch.appfeeder.repository.merchant;

import com.lunch.appfeeder.model.entity.merchant.Merchant;
import com.lunch.appfeeder.model.entity.merchant.Merchant_MerchantStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IMerchantMerchantStatusRepository extends JpaRepository<Merchant_MerchantStatus,Long> {
    public List<Merchant_MerchantStatus> findMerchant_MerchantStatusByMerchant(Merchant merchant);
}
