package com.lunch.appfeeder.repository.test;

import com.lunch.appfeeder.model.entity.merchant.Merchant_MerchantStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IMMSRepo extends JpaRepository<Merchant_MerchantStatus,Long> {
}
