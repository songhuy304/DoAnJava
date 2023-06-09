package com.example.doanjava.services;

import com.example.doanjava.entity.Invoice;
import com.example.doanjava.entity.User;
import com.example.doanjava.entity.product;
import com.example.doanjava.repository.IInvoiceRepository;
import com.example.doanjava.repository.IItemInvoiceRepository;
import com.example.doanjava.repository.IProductRepository;
import com.example.doanjava.repository.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service

public class InvoiceService {
    @Autowired
    private IInvoiceRepository invoiceRepository;
    @Autowired
    private IItemInvoiceRepository iItemInvoiceRepository;
    @Autowired
    private IProductRepository productRepository;
    @Autowired
    private IUserRepository userRepository;
    public List<Invoice> getAllorder(){
        return invoiceRepository.findAll();
    }
    public Page<Invoice> findAll(Pageable pageable) {
        return invoiceRepository.findAll(pageable);
    }

    public product getProductById(Long productId) {
        return productRepository.findById(productId).orElse(null);
    }
    public Integer getTotalCount() {
        long count = invoiceRepository.countByIdNotNull();
        return Math.toIntExact(count);
    }
    public Integer getTotalPrice() {
        Integer totalPrice = invoiceRepository.getTotalPrice();

        // Xử lý kết quả theo nhu cầu của bạn
        // Ví dụ:
        if (totalPrice == null) {
            totalPrice = 0;
        }

        return totalPrice;
    }
    public Invoice getOdrerId(Long id){
        Optional<Invoice> optional = invoiceRepository.findById(id);
        return optional.orElse(null);

    }


//    public List<Invoice> searchProduct(String keyword) {
//        return invoiceRepository.searchProduct(keyword);
//    }

    public List<Invoice> getInvoicesByUserId(Long userId) {
        return invoiceRepository.findByUserId(userId);
    }
    public List<Invoice> getUserInvoices() {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.isAuthenticated()) {
            String currentUserName = authentication.getName();


            User currentUser = userRepository.findByUserName(currentUserName);

            if (currentUser != null) {

                List<Invoice> invoices = invoiceRepository.findByUserId(currentUser.getId());
                return invoices;
            }
        }

        return Collections.emptyList();
    }
    public void updateBook(Invoice invoice){
        invoiceRepository.save(invoice);

    }
//    public List<Invoice> getAllproducts(Integer pageNo,
//                                        Integer pageSize,
//                                        String sortBy) {
//
//        return invoiceRepository.findAllBooks(pageNo, pageSize, sortBy);
//    }
    public void deleteproduct(Long id){
        invoiceRepository.deleteById(id);
    }
}
