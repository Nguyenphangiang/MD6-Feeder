package com.lunch.appfeeder.repository;

import com.lunch.appfeeder.model.entity.InvoiceStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IInvoiceStatusRepository extends JpaRepository<InvoiceStatus,Long> {
}
