package com.example.doanjava.Controller;

import com.example.doanjava.daos.Item;
import com.example.doanjava.entity.Category;
import com.example.doanjava.entity.product;
import com.example.doanjava.repository.IProductRepository;
import com.example.doanjava.services.CartService;
import com.example.doanjava.services.productService;
import com.example.doanjava.services.CategoryService;

import jakarta.servlet.http.HttpSession;
import org.antlr.v4.runtime.misc.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/products")
public class ProductController {
    @Autowired
    private CartService cartService;
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

        List<Category> menuleft = categoryService.getAllcategory();
        model.addAttribute("menuleft",menuleft);

        model.addAttribute("categories",
                categoryService.getAllcategory());
        int totalProducts = productsService.getAllproducts(pageNo, pageSize, sortBy).size();
        int totalPages = (int) Math.ceil((double) totalProducts / pageSize);

        model.addAttribute("totalPages", totalPages);
        return "product/sanpham";
    }

    @GetMapping("/searchid/{categoryId}")
    public String searchBookbyid(
            @NotNull Model model,
            @PathVariable("categoryId") Long categoryId,

            @RequestParam(defaultValue = "0") Integer pageNo,
            @RequestParam(defaultValue = "10") Integer pageSize,
            @RequestParam(defaultValue = "id") String sortBy) {
        model.addAttribute("products", productsService.searchProductByCategoryId(categoryId));

        model.addAttribute("currentPage", pageNo);
        List<Category> menuleft = categoryService.getAllcategory();
        model.addAttribute("menuleft",menuleft);

        model.addAttribute("categories",
                categoryService.getAllcategory());
        model.addAttribute("totalPages",
                productsService
                        .getAllproducts(pageNo, pageSize, sortBy)
                        .size() / pageSize);
        return "product/sanpham";
    }


    @GetMapping("/search")
    public String searchBook(
            @NotNull Model model,
            @RequestParam String keyword,
            @RequestParam(defaultValue = "0") Integer pageNo,
            @RequestParam(defaultValue = "10") Integer pageSize,
            @RequestParam(defaultValue = "id") String sortBy) {
        model.addAttribute("products", productsService.searchProduct(keyword));
        model.addAttribute("currentPage", pageNo);
        List<Category> menuleft = categoryService.getAllcategory();
        model.addAttribute("menuleft",menuleft);

        model.addAttribute("categories",
                categoryService.getAllcategory());
        model.addAttribute("totalPages",
                productsService
                        .getAllproducts(pageNo, pageSize, sortBy)
                        .size() / pageSize);
        return "product/sanpham";
    }

    @PostMapping("/add-to-cart")
    public String addToCart(HttpSession session,
                            @RequestParam long id,
                            @RequestParam String name,
                            @RequestParam double price,
                            @RequestParam(defaultValue = "1") int quantity)
    {
        var cart = cartService.getCart(session);
        cart.addItems(new Item(id, name, price, quantity ));
        cartService.updateCart(session, cart);
        return "redirect:/products";
    }
    @GetMapping("/single/{id}")
    public String test(@PathVariable("id") Long id , Model model){
        product item =  productsService.getProductSingle(id);
        model.addAttribute("single" , item);
        return "product/single";
    }




}
