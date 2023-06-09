package com.example.doanjava.Controller.Admin;

import com.example.doanjava.entity.Invoice;
import com.example.doanjava.entity.ItemInvoice;
import com.example.doanjava.entity.product;
import com.example.doanjava.services.CategoryService;
import com.example.doanjava.services.InvoiceService;
import com.example.doanjava.services.ItemInvoiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Optional;
@Controller
@PreAuthorize("hasAuthority('ADMIN')")

@RequestMapping("/admin/Order")
public class OderAdminController {
    @Autowired
    private InvoiceService invoiceService;
    @Autowired
    private ItemInvoiceService itemInvoiceService;
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
    @GetMapping("/view/{id}")
    public String Chitietdonhang(@PathVariable("id") Long id , Model model){
        Invoice item =  invoiceService.getOdrerId(id);
        List<ItemInvoice> invoiceItems = itemInvoiceService.getInvoiceItemsByInvoiceId(id);
        model.addAttribute("single" , item);
        model.addAttribute("invoiceItems", invoiceItems);
        return "Admin/Orderdetail";
    }
}
