package com.lunch.appfeeder.repository.merchant;

import com.lunch.appfeeder.model.entity.Customer;
import com.lunch.appfeeder.model.entity.merchant.Merchant;
import com.lunch.appfeeder.model.login.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IMerchantRepository extends JpaRepository<Merchant,Long> {
    Iterable<Merchant> findMerchantByNameContaining(String nameMerchant);

//    @Query( value = "select m.id, m.address, m.name, m.phone, m.safe_food_license, m.status, m.user_id from merchants as m join user u on u.id = m.user_id where u.id = ?1",nativeQuery = true)
    Merchant findMerchantByUser_Id(Long id);

    Merchant findMerchantByUserUsername(String name);
}
