package com.lunch.appfeeder.service.merchant;

import com.lunch.appfeeder.model.entity.merchant.Merchant;
import com.lunch.appfeeder.model.entity.merchant.MerchantStatus;
import com.lunch.appfeeder.service.IGeneralService;

public interface IMerchantService extends IGeneralService<Merchant> {
     Merchant setMerchantStatus(Merchant merchant, MerchantStatus merchantStatus);
     Iterable<Merchant> findMerchantByNameContaining(String name);
     Merchant findMerchantByUser_Id(Long id);
     Merchant findMerchantByUserUsername(String name);
}
