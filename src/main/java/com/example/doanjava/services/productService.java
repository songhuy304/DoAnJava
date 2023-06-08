package com.example.doanjava.services;

import com.example.doanjava.entity.Category;
import com.example.doanjava.repository.IProductRepository;
import com.example.doanjava.entity.product;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Base64;
import java.util.List;
import java.util.Optional;



@Service
public class productService {

    @Autowired
    private IProductRepository productRepository;
    public List<product> getAllProduct(){
        List<product> productList = productRepository.findAll();

        // Iterate over the products and add the complete base64 image string
        for (product product1 : productList) {
            String base64Image = "data:image/jpeg;base64," + product1.getImage();
            product1.setCompleteImage(base64Image);
        }

        return productList;
    }
    public List<product> getAllmoi(Integer pageNo,
                                        Integer pageSize,
                                        String sortBy) {
        List<product> productList = productRepository.findAllBooks(pageNo, pageSize, sortBy);
        for (product product1 : productList) {
            String base64Image = "data:image/jpeg;base64," + product1.getImage();
            product1.setCompleteImage(base64Image);
        }
        return productList;
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
    public void Addproduct(product products ,  MultipartFile file){
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());
        try {
            products.setImage(Base64.getEncoder().encodeToString(file.getBytes()));

        }catch (IOException e)
        {
            e.printStackTrace();
        }
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
