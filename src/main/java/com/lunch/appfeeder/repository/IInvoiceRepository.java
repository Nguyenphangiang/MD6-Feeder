package com.lunch.appfeeder.repository;

import com.lunch.appfeeder.model.entity.Invoice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface IInvoiceRepository extends JpaRepository<Invoice,Long> {
    Iterable<Invoice> findAllByCustomer_Id(Long idCustomer);
    Iterable<Invoice> findAllByMerchantId(Long idMerchant);

    @Query(value = "select * from invoices i left join customers c on c.id = i.customer_id where c.name like ?1",nativeQuery = true)
    Iterable<Invoice> findAllByCustomer_Name(String name);
    @Query(value = "select * from invoices i left join customers c on c.id = i.customer_id where c.phone like ?1",nativeQuery = true)
    Iterable<Invoice> findAllByCustomer_Phone(String phone);

}
