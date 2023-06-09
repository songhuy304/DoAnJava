package com.example.doanjava.repository;

import com.example.doanjava.entity.ItemInvoice;
import com.example.doanjava.services.InvoiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IItemInvoiceRepository extends
        JpaRepository<ItemInvoice, Long>{
    List<ItemInvoice> findByInvoiceId(Long invoiceId);
}