package com.example.doanjava.Controller.Admin;

import com.example.doanjava.entity.Invoice;
import com.example.doanjava.entity.product;
import com.example.doanjava.services.CategoryService;
import com.example.doanjava.services.InvoiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Optional;
@Controller
@RequestMapping("/admin/Order")
public class OderAdminController {
    @Autowired
    private InvoiceService invoiceService;
    @GetMapping("/page")
    public String showAllproduct(Model model , @RequestParam("p") Optional<Integer> p ){
        Pageable pageable = (Pageable) PageRequest.of(p.orElse(0) ,10);
        Page<Invoice> page = invoiceService.findAll(pageable);
        model.addAttribute("LIST_Invoice",page);
        return "Admin/Order";
    }
}
