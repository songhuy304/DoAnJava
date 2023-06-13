package com.example.doanjava.repository;


import com.example.doanjava.entity.Invoice;
import com.example.doanjava.entity.product;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface IInvoiceRepository extends JpaRepository<Invoice,
        Long>{
    @Query("SELECT SUM(i.price) FROM Invoice i")
    Integer getTotalPrice();
    long countByIdNotNull();
    List<Invoice> findByInvoiceDateBetween(Date startDate, Date endDate);

    List<Invoice> findByUserId(Long userId);
    default List<Invoice> findAllBooks(Integer pageNo,
                                       Integer pageSize,
                                       String sortBy) {
        return findAll(PageRequest.of(pageNo,
                pageSize,
                Sort.by(sortBy)))

                .getContent();
    }
}