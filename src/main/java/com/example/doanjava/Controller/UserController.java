package com.example.doanjava.Controller;


import com.example.doanjava.entity.Category;
import com.example.doanjava.entity.Invoice;
import com.example.doanjava.entity.ItemInvoice;
import com.example.doanjava.services.InvoiceService;
import com.example.doanjava.services.ItemInvoiceService;
import jakarta.validation.Valid;
import org.antlr.v4.runtime.misc.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import com.example.doanjava.entity.User;
import  com.example.doanjava.services.UserService;

import java.util.List;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
@Controller
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private InvoiceService invoiceService;
    @Autowired
    private ItemInvoiceService itemInvoiceService;

    @GetMapping("/login")
    public String login() {
        return "user/login";
    }

    @GetMapping("/register")
    public String register(Model model) {
        model.addAttribute("user", new User());
        return "user/register";
    }

    @PostMapping("/register")
    public String register(@Valid @ModelAttribute("user") User user, BindingResult bindingResult,Model model){
        if (bindingResult.hasErrors()){
            bindingResult.getFieldErrors().forEach(error
                    ->model.addAttribute(error.getField()+"_error", error.getDefaultMessage()));
            return "user/register";
        }
        user.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));
        userService.save(user);
        return "redirect:/login";
    }
    @GetMapping("/admin/user")
    public String showAllBooks(
            @NotNull Model model,
            @RequestParam(defaultValue = "0") Integer pageNo,
            @RequestParam(defaultValue = "10") Integer pageSize,
            @RequestParam(defaultValue = "id") String sortBy) {
        model.addAttribute("user", userService.getAllmoi(pageNo,
                pageSize, sortBy));
        model.addAttribute("currentPage", pageNo);
        int totalProducts = userService.getAllmoi(pageNo, pageSize, sortBy).size();
        int totalPages = (int) Math.ceil((double) totalProducts / pageSize);

        model.addAttribute("totalPages", totalPages);
        return "Admin/User";
    }
    @GetMapping("/thongtindonhang")
    public String showAllBooks1(
            @NotNull Model model
           ) {
        model.addAttribute("user", invoiceService.getUserInvoices());
        return "product/donhang";
    }
    @GetMapping("/thongtindonhang/view/{id}")
    public String Chitietdonhangne(@PathVariable("id") Long id , Model model){
        Invoice item =  invoiceService.getOdrerId(id);
        List<ItemInvoice> invoiceItems = itemInvoiceService.getInvoiceItemsByInvoiceId(id);
        model.addAttribute("donhang" , item);
        model.addAttribute("invoiceItems1", invoiceItems);
        return "product/Orderdetail";
    }
}
