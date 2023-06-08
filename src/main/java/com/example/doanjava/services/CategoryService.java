package com.example.doanjava.services;


import com.example.doanjava.entity.Category;
import com.example.doanjava.entity.product;
import com.example.doanjava.repository.ICategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Base64;
import java.util.List;
import java.util.Optional;
@Service
public class CategoryService {
    @Autowired
    private ICategoryRepository categoryRepository;
    public List<Category> getAllcategory(){
        List<Category>categories = categoryRepository.findAll();

        // Iterate over the products and add the complete base64 image string
        for (Category category : categories) {
            String base64Image = "data:image/jpeg;base64," + category.getImage();
            category.setCompleteImage(base64Image);
        }

        return categories;
    }
    public Category getcategoryId(Long id){
        Optional<Category> optionalCategory = categoryRepository.findById(id);
        if(optionalCategory.isPresent()){
            return optionalCategory.get();

        }else {
            throw new RuntimeException("Category is not funot");
        }


    }
    public void AddCate(Category category  , MultipartFile file){
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());
        try {
            category.setImage(Base64.getEncoder().encodeToString(file.getBytes()));

        }catch (IOException e)
        {
            e.printStackTrace();
        }
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
