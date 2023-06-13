package com.example.doanjava.Controller.Admin;


import com.example.doanjava.entity.Category;
import com.example.doanjava.entity.product;
import com.example.doanjava.services.CategoryService;
import com.example.doanjava.services.productService;
import org.antlr.v4.runtime.misc.NotNull;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import org.springframework.web.bind.annotation.*;

import org.springframework.web.multipart.MultipartFile;



@Controller
@PreAuthorize("hasAuthority('ADMIN')")
@RequestMapping("/admin/product")
public class ProductAdminController {
    @Autowired
    private productService productsService;
    @Autowired
    private CategoryService categoryService;
    @GetMapping()
    public String showAllBooks(
            @NotNull Model model,
            @RequestParam(defaultValue = "0") Integer pageNo,
            @RequestParam(defaultValue = "10") Integer pageSize,
            @RequestParam(defaultValue = "id") String sortBy) {
        model.addAttribute("products", productsService.getAllmoi(pageNo,
                pageSize, sortBy));
        model.addAttribute("currentPage", pageNo);
        model.addAttribute("categories",
                categoryService.getAllcategory());
        int totalProducts = productsService.getAllproducts(pageNo, pageSize, sortBy).size();
        int totalPages = (int) Math.ceil((double) totalProducts / pageSize);

        model.addAttribute("totalPages", totalPages);
        return "Admin/Product";
    }



    @GetMapping("/add")
    public String addproductForm(Model model){
        model.addAttribute("products",new product());
        model.addAttribute("categories",categoryService.getAllcategory());
        return "Admin/add";
    }

    @PostMapping("/add")

    public String addproduct(@ModelAttribute("products") product products,@RequestParam("file") MultipartFile file){
        productsService.Addproduct(products ,file);

        return "redirect:/admin/product";

    }
    @GetMapping("/delete/{id}")
    public String deleteproduct(@PathVariable("id")  Long id){

        productsService.deleteproduct(id);
        return "redirect:/admin/product";
    }
    @GetMapping("/edit/{id}")
    public String editproductForm(@PathVariable("id") Long id, Model model) {
        product product = productsService.getproductId(id);
        model.addAttribute("categories", categoryService.getAllcategory());


        model.addAttribute("product", product);
        return "Admin/edit";
    }
    @PostMapping("/edit/{id}")
    public String editproduct(@PathVariable("id") Long id, @ModelAttribute("product") product product ,@RequestParam("file") MultipartFile file) {
        product product1 = productsService.getproductId(id);

        product1.setTitle(product.getTitle());
        product1.setDescription(product.getDescription());
        product1.setQuantity(product.getQuantity());
        product1.setPrice(product.getPrice());

        product1.setCategory(product.getCategory());


        productsService.Addproduct(product1 ,file);
        return "redirect:/admin/product";
    }









}
