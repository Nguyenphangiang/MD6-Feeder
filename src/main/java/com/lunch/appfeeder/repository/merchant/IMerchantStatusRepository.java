package com.lunch.appfeeder.repository.merchant;

import com.lunch.appfeeder.model.entity.merchant.MerchantStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IMerchantStatusRepository extends JpaRepository<MerchantStatus,Long> {
}
