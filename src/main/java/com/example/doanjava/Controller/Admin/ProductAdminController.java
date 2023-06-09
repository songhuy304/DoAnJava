package com.example.doanjava.Controller.Admin;


import com.example.doanjava.entity.Category;
import com.example.doanjava.entity.product;
import com.example.doanjava.services.CategoryService;
import com.example.doanjava.services.productService;
import org.antlr.v4.runtime.misc.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.Banner;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

import java.util.Optional;

import java.util.List;
import java.util.stream.Collectors;

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

//    @GetMapping("/page")
//    public String showAllproduct(Model model , @RequestParam("p") Optional<Integer> p ){
//        Pageable pageable = (Pageable) PageRequest.of(p.orElse(0) ,10);
//        Page<product> page = productsService.findAll(pageable);
//        model.addAttribute("LIST_PRODUCT",page);
//        return "Admin/Product";
//    }

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
        return "redirect:/admin/product/page";
    }
    @GetMapping("/edit/{id}")
    public String editproductForm(@PathVariable("id") Long id, Model model) {
        product product = productsService.getproductId(id);
        model.addAttribute("products", product);
        model.addAttribute("categories", categoryService.getAllcategory());
        return "Admin/edit";
    }
    @PostMapping("/edit/{id}")
    public String editproduct(@PathVariable("id") Long id, @ModelAttribute("product") product product) {
        product existingProduct1 = productsService.getproductId(id);
        existingProduct1.setTitle(product.getTitle());
        existingProduct1.setDescription(product.getDescription());
        existingProduct1.setImage(product.getImage());
        existingProduct1.setCategory(product.getCategory());
        existingProduct1.setPrice(product.getPrice());
        existingProduct1.setQuantity(product.getQuantity());
        existingProduct1.setIsactive(product.getIsactive());


        productsService.updateBook(existingProduct1);
        return "redirect:admin/product/page";
    }









}
