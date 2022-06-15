package com.lunch.appfeeder.service.merchant;

import com.lunch.appfeeder.model.entity.merchant.Merchant;
import com.lunch.appfeeder.model.entity.merchant.MerchantStatus;
import com.lunch.appfeeder.repository.merchant.IMerchantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
public class MerchantService implements IMerchantService {
    @Autowired
    private IMerchantRepository merchantRepository;

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
        return merchantRepository.save(merchant);
    }

    @Override
    public Merchant setMerchantStatus(Merchant merchant, MerchantStatus merchantStatus) {
        merchant.setStatus(merchantStatus);
        return merchantRepository.save(merchant) ;
    }

    @Override
    public Iterable<Merchant> findMerchantByNameContaining(String name) {
        return merchantRepository.findMerchantByNameContaining(name);
    }

    @Override
    public Merchant findMerchantByUser_Id(Long id) {
        return merchantRepository.findMerchantByUser_Id(id);
    }

    @Override
    public Merchant findMerchantByUserUsername(String name) {
        return merchantRepository.findMerchantByUserUsername(name);
    }

    @Override
    public Iterable<Merchant> findAllByGoldPartnerTrue() {
        return merchantRepository.findAllByGoldPartnerTrue();
    }

    @Override
    public void remove(Long id) {
//        Merchant merchant = merchantRepository.getById(id);
//        List<Merchant_MerchantStatus> merchantStatuses = merchantLinkStatus.findMerchant_MerchantStatusByMerchant(merchant);
//        for (Merchant_MerchantStatus merchantStatus:merchantStatuses
//             ) {
//            merchantLinkStatus.deleteById(merchantStatus.getId());
//        }
        merchantRepository.deleteById(id);
    }
}
