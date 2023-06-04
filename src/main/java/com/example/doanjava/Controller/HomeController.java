package com.example.doanjava.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class HomeController {
    @GetMapping("/home")
    public String home(){

        return "product/home";
    }
    @GetMapping("/_layout")
    public String layout(){
        return "_Layout";
    }
}
