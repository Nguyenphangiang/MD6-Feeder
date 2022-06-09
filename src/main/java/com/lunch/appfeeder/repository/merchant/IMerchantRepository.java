package com.lunch.appfeeder.repository.merchant;

import com.lunch.appfeeder.model.entity.merchant.Merchant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IMerchantRepository extends JpaRepository<Merchant,Long> {
}
