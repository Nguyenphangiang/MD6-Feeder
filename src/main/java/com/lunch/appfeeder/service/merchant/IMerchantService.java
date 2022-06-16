package com.lunch.appfeeder.service.merchant;

import com.lunch.appfeeder.model.entity.DTO.IMerchantSaleAmount;
import com.lunch.appfeeder.model.entity.merchant.Merchant;
import com.lunch.appfeeder.model.entity.merchant.MerchantStatus;
import com.lunch.appfeeder.service.IGeneralService;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.Optional;

public interface IMerchantService extends IGeneralService<Merchant> {
     Merchant setMerchantStatus(Merchant merchant, MerchantStatus merchantStatus);
     Iterable<Merchant> findMerchantByNameContaining(String name);
     Merchant findMerchantByUser_Id(Long id);
     Merchant findMerchantByUserUsername(String name);
     Iterable<Merchant> findAllByGoldPartnerTrue ();
     Iterable<IMerchantSaleAmount> getMerchantSaleAmountByYear(int year);
     Iterable<IMerchantSaleAmount> getMerchantSaleAmountByMonth(int month);
     Iterable<IMerchantSaleAmount> getMerchantSaleAmountByQuarter(int quarter);
}
