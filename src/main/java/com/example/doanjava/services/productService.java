package com.example.doanjava.services;

import com.example.doanjava.repository.IProductRepository;
import com.example.doanjava.entity.product;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class productService {

    @Autowired
    private IProductRepository productRepository;
    public List<product> getAllproduct(){
        return productRepository.findAll();
    }
    public Page<product> findAll(Pageable pageable) {
        return productRepository.findAll(pageable);
    }
    public product getproductId(Long id){
        Optional<product> optional = productRepository.findById(id);
        return optional.orElse(null);

    }


    public List<product> searchProduct(String keyword) {
        return productRepository.searchProduct(keyword);
    }
    public List<product> searchProductByCategoryId(Long categoryId) {
        return productRepository.searchProductByCategoryId(categoryId);
    }
    public void Addproduct(product products){
        productRepository.save(products);

    }
    public void updateBook(product products){
        productRepository.save(products);

    }
    public List<product> getAllproducts(Integer pageNo,
                                  Integer pageSize,
                                  String sortBy) {

        return productRepository.findAllBooks(pageNo, pageSize, sortBy);
    }
    public void deleteproduct(Long id){
        productRepository.deleteById(id);
    }



}
