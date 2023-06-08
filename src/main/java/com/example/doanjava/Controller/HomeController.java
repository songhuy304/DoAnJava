package com.example.doanjava.Controller;

import com.example.doanjava.entity.Category;
import com.example.doanjava.entity.product;
import com.example.doanjava.services.productService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/")
public class HomeController {
    @Autowired
    private productService productsService;
    @GetMapping("/home")
    public String home(Model model){
        List<product> listproduct = productsService.getAllProduct();
        List<product> firstFiveProducts = listproduct.subList(0, Math.min(listproduct.size(), 5));
        model.addAttribute("list", firstFiveProducts);
        return "product/home";
    } @GetMapping("/trangchu")
    public String trangchu( ){

        return "product/home";
    }

    @GetMapping("/_layout")
    public String layout(){
        return "_Layout";
    }
}
