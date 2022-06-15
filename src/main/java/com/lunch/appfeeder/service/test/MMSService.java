package com.lunch.appfeeder.service.test;

import com.lunch.appfeeder.model.entity.merchant.Merchant_MerchantStatus;
import com.lunch.appfeeder.repository.test.IMMSRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class MMSService implements IMMSService{
    @Autowired
    private IMMSRepo immsRepo;

    @Override
    public Iterable<Merchant_MerchantStatus> findAll() {
        return null;
    }

    @Override
    public Optional<Merchant_MerchantStatus> findById(Long id) {
        return Optional.empty();
    }

    @Override
    public Merchant_MerchantStatus save(Merchant_MerchantStatus merchant_merchantStatus) {
        return immsRepo.save(merchant_merchantStatus);
    }

    @Override
    public void remove(Long id) {

    }
}
