package com.lunch.appfeeder.service.merchant;

import com.lunch.appfeeder.model.entity.merchant.MerchantStatus;
import com.lunch.appfeeder.repository.merchant.IMerchantStatusRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MerchantStatusService {
    @Autowired
    private IMerchantStatusRepository merchantStatusRepository;

    public Iterable<MerchantStatus> findAll(){
        return merchantStatusRepository.findAll();
    }
}
