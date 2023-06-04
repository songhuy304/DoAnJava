package com.example.doanjava.Controller.Admin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/trangchu")
public class AdminController {
    public String index(){
        return "Admin/home";
    }
}
