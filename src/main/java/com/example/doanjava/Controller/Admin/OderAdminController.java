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

import java.util.List;
import java.util.Optional;
@Controller
@RequestMapping("/admin/Order")
public class OderAdminController {
    @Autowired
    private InvoiceService invoiceService;
    @GetMapping()
    public String showAllproduct(Model model ){
        List<Invoice> list = invoiceService.getAllorder();
        model.addAttribute("LIST_Invoice",list);

        Integer totalPrice = invoiceService.getTotalPrice();
        model.addAttribute("total" ,totalPrice );
        Integer  countMaDH = invoiceService.getTotalCount();
        model.addAttribute("countDH" ,countMaDH );

        return "Admin/Order";
    }
}
