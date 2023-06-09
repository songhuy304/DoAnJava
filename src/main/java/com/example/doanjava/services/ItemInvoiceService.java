package com.example.doanjava.services;


import com.example.doanjava.entity.ItemInvoice;
import com.example.doanjava.repository.IInvoiceRepository;
import com.example.doanjava.repository.IItemInvoiceRepository;
import com.example.doanjava.repository.IProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service

public class ItemInvoiceService {
    @Autowired
    private IInvoiceRepository invoiceRepository;
    @Autowired
    private IItemInvoiceRepository iItemInvoiceRepository;
    @Autowired
    private IProductRepository productRepository;


    public List<ItemInvoice> getInvoiceItemsByInvoiceId(Long invoiceId) {
        return iItemInvoiceRepository.findByInvoiceId(invoiceId);
    }
}