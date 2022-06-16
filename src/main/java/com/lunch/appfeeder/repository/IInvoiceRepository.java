package com.lunch.appfeeder.repository;

import com.lunch.appfeeder.model.entity.Invoice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IInvoiceRepository extends JpaRepository<Invoice,Long> {
    Iterable<Invoice> findAllByCustomer_Id(Long idCustomer);
    Iterable<Invoice> findAllByMerchantId(Long idMerchant);
}
