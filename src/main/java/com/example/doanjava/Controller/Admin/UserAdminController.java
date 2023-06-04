package com.example.doanjava.Controller.Admin;

import com.example.doanjava.entity.User;
import com.example.doanjava.services.UserService;
import com.example.doanjava.services.productService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import java.util.List;

@Controller
public class UserAdminController {
    @Autowired
    private UserService userService;
    public String home(Model model){
        List<User> users = userService.getAlluser();
        model.addAttribute("listuser", users);
        return "Admin/user";
    }
}
