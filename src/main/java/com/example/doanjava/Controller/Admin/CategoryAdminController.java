package com.example.doanjava.Controller.Admin;

import com.example.doanjava.entity.Category;
import com.example.doanjava.entity.product;
import com.example.doanjava.services.CategoryService;
import com.example.doanjava.services.productService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/admin/category")
public class CategoryAdminController {
    @Autowired
    private productService productsService;
    @Autowired
    private CategoryService categoryService;
    @GetMapping()
    public String showAllcate(Model model){
        List<Category> categories = categoryService.getAllcategory();
        model.addAttribute("categories",categories);
        return "Admin/category/hienthi";
    }
    @GetMapping("/add")
    public String addcate(Model model){
        model.addAttribute("categories" , new Category());
        return "Admin/category/add";
    }
    @PostMapping("/add")
    public String addcategory(@ModelAttribute("categories") Category category){
        categoryService.saveCategory(category);

        return "redirect:/admin/category/add";
    }
    @GetMapping("/delete/{id}")
    public String deletecate(@PathVariable("id") Long id ){
        categoryService.deleteBook(id);
        return "redirect:/admin/category";

    }

}
