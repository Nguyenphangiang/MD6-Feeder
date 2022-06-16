package com.lunch.appfeeder.repository.merchant;

import com.lunch.appfeeder.model.entity.Customer;
import com.lunch.appfeeder.model.entity.DTO.IMerchantSaleAmount;
import com.lunch.appfeeder.model.entity.merchant.Merchant;
import com.lunch.appfeeder.model.login.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Repository
public interface IMerchantRepository extends JpaRepository<Merchant,Long> {
    Iterable<Merchant> findMerchantByNameContaining(String nameMerchant);

//    @Query( value = "select m.id, m.address, m.name, m.phone, m.safe_food_license, m.status, m.user_id from merchants as m join user u on u.id = m.user_id where u.id = ?1",nativeQuery = true)
    Merchant findMerchantByUser_Id(Long id);

    Merchant findMerchantByUserUsername(String name);

    Iterable<Merchant> findAllByGoldPartnerTrue ();

    @Query(nativeQuery = true, value = "select i.date,i.customer_id as customer,i.invoice_status_id " +
            "as invoice,d.id as dish,sum(d.price) as total from invoices as i " +
            "join merchants m on m.id = i.merchant_id " +
            "join orders o on i.customer_id = o.customer_id " +
            "join dishes d on i.merchant_id = d.merchant_id " +
            "WHERE YEAR(i.date)= :year group by YEAR(i.date)")
    Iterable<IMerchantSaleAmount> getMerchantSaleAmountByYear(@Param("year") int year);

    @Query(nativeQuery = true, value = "select i.date, i.customer_id as customer, i.invoice_status_id " +
            "as invoice, d.id as dish, sum(d.price) as total from invoices as i " +
            "join merchants m on m.id = i.merchant_id " +
            "join orders o on i.customer_id = o.customer_id " +
            "join dishes d on i.merchant_id = d.merchant_id " +
            "WHERE MONTH(i.date) = :month group by MONTH(i.date)")
    Iterable<IMerchantSaleAmount> getMerchantSaleAmountByMonth(@Param("month") int month);
    @Query(nativeQuery = true, value = "select i.date, i.customer_id as customer, i.invoice_status_id " +
            "as invoice, d.id as dish, sum(d.price) as total from invoices as i " +
            "join merchants m on m.id = i.merchant_id " +
            "join orders o on i.customer_id = o.customer_id " +
            "join dishes d on i.merchant_id = d.merchant_id " +
            "WHERE QUARTER(i.date) = :quarter group by QUARTER(i.date)")
    Iterable<IMerchantSaleAmount> getMerchantSaleAmountByQuarter(@Param("quarter") int quarter);
}

