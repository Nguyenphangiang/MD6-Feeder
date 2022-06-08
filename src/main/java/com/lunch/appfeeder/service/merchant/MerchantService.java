package com.lunch.appfeeder.service.merchant;

import com.lunch.appfeeder.model.entity.merchant.Merchant;
import com.lunch.appfeeder.model.entity.merchant.MerchantStatus;
import com.lunch.appfeeder.model.entity.merchant.Merchant_MerchantStatus;
import com.lunch.appfeeder.repository.merchant.IMerchantMerchantStatusRepository;
import com.lunch.appfeeder.repository.merchant.IMerchantRepository;
import com.lunch.appfeeder.repository.merchant.IMerchantStatusRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MerchantService implements IMerchantService {
    @Autowired
    private IMerchantRepository merchantRepository;

    @Autowired
    private IMerchantStatusRepository merchantStatusRepository;

    @Autowired
    private IMerchantMerchantStatusRepository merchantLinkStatus;

    @Override
    public Iterable<Merchant> findAll() {
        return merchantRepository.findAll();
    }

    @Override
    public Optional<Merchant> findById(Long id) {
        return merchantRepository.findById(id);
    }

    @Override
    public Merchant save(Merchant merchant) {
        return null;
    }

    public Merchant save(Merchant merchant, MerchantStatus merchantStatus) {
        Merchant_MerchantStatus mms = new Merchant_MerchantStatus(merchant,merchantStatus);
        merchantLinkStatus.save(mms);
        return merchantRepository.save(merchant);
    }

    @Override
    public void remove(Long id) {
        Merchant merchant = merchantRepository.getById(id);
        List<Merchant_MerchantStatus> merchantStatuses = merchantLinkStatus.findMerchant_MerchantStatusByMerchant(merchant);
        for (Merchant_MerchantStatus merchantStatus:merchantStatuses
             ) {
            merchantLinkStatus.deleteById(merchantStatus.getId());
        }
        merchantRepository.deleteById(id);
    }
}
