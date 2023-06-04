package com.example.doanjava.services;


import com.example.doanjava.entity.Category;
import com.example.doanjava.entity.product;
import com.example.doanjava.repository.ICategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class CategoryService {
    @Autowired
    private ICategoryRepository categoryRepository;
    public List<Category> getAllcategory(){
        return categoryRepository.findAll();
    }
    public Category getcategoryId(Long id){
        Optional<Category> optionalCategory = categoryRepository.findById(id);
        if(optionalCategory.isPresent()){
            return optionalCategory.get();

        }else {
            throw new RuntimeException("Category is not funot");
        }


    }
    public void AddCate(Category category){
        categoryRepository.save(category);

    }
    public void updatecate(Category category){
        categoryRepository.save(category);

    }
    public Category saveCategory(Category categorys){
        return categoryRepository.save(categorys);
    }

    public void deleteBook(Long id){
        categoryRepository.deleteById(id);
    }
}
