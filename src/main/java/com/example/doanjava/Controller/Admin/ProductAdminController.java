package com.example.doanjava.Controller.Admin;

import com.example.doanjava.entity.Category;
import com.example.doanjava.entity.product;
import com.example.doanjava.services.CategoryService;
import com.example.doanjava.services.productService;
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
    @GetMapping("/page")
    public String showAllproduct(Model model , @RequestParam("p") Optional<Integer> p ){
        Pageable pageable = (Pageable) PageRequest.of(p.orElse(0) ,10);
        Page<product> page = productsService.findAll(pageable);
        model.addAttribute("LIST_PRODUCT",page);
        return "Admin/Product";
    }
    @GetMapping("/show")
    public String showAllishome(Model model){
        List<product> items = productsService.getAllproduct().stream()
                .filter(product -> Boolean.TRUE.equals(product.getIsactive()))
                .collect(Collectors.toList());
        model.addAttribute("items", items);
        return "Admin/test";
    }
    @GetMapping("/add")
    public String addproductForm(Model model){
        model.addAttribute("product",new product());
        model.addAttribute("categories",categoryService.getAllcategory());
        return "Admin/add";
    }

    @PostMapping("/add")

    public String addproduct(@ModelAttribute("product") product product){
        productsService.Addproduct(product);

        return "redirect:/admin/product/page";

    }
    @GetMapping("/delete/{id}")
    public String deleteproduct(@PathVariable("id")  Long id){

        productsService.deleteproduct(id);
        return "redirect:/admin/product/page";
    }






}
