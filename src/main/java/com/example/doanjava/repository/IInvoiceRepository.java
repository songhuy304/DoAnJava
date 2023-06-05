package com.example.doanjava.repository;


import com.example.doanjava.entity.Invoice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
@Repository
public interface IInvoiceRepository extends JpaRepository<Invoice,
        Long>{
    @Query("SELECT SUM(i.price) FROM Invoice i")
    Integer getTotalPrice();
    long countByIdNotNull();
}