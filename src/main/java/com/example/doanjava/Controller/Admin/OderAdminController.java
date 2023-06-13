package com.example.doanjava.Controller.Admin;

import com.example.doanjava.entity.Invoice;
import com.example.doanjava.entity.ItemInvoice;
import com.example.doanjava.entity.product;
import com.example.doanjava.services.CategoryService;
import com.example.doanjava.services.InvoiceService;
import com.example.doanjava.services.ItemInvoiceService;
import org.antlr.v4.runtime.misc.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
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
    public String showAllproduct(  @NotNull Model model,
                                   @RequestParam(defaultValue = "0") Integer pageNo,
                                   @RequestParam(defaultValue = "10") Integer pageSize,
                                   @RequestParam(defaultValue = "id") String sortBy){

        model.addAttribute("LIST_Invoice", invoiceService.getAllmoi(pageNo,
                pageSize, sortBy));
        model.addAttribute("currentPage", pageNo);
        int totalProducts = invoiceService.getAllmoi(pageNo, pageSize, sortBy).size();
        int totalPages = (int) Math.ceil((double) totalProducts / pageSize);

        model.addAttribute("totalPages", totalPages);


        Integer totalPrice = invoiceService.getTotalPrice();
        model.addAttribute("total" ,totalPrice );
        Integer  countMaDH = invoiceService.getTotalCount();
        model.addAttribute("countDH" ,countMaDH );

        return "Admin/Order";
    }

    @PostMapping("/filter")
    public String filterOrdersByDateRange(@RequestParam("startDate") String startDate,
                                          @RequestParam("endDate") String endDate,
                                          Model model) {

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date parsedStartDate = null;
        Date parsedEndDate = null;
        try {
            parsedStartDate = sdf.parse(startDate);
            parsedEndDate = sdf.parse(endDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }


        List<Invoice> filteredList = invoiceService.getOrderByDate(parsedStartDate, parsedEndDate);
        model.addAttribute("LIST_Invoice", filteredList);


        Double totalPrice = invoiceService.getTotalPriceByDate(parsedStartDate, parsedEndDate);
        model.addAttribute("total", totalPrice);


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
    @GetMapping("/edit/{id}")
    public String editinvoiceForm(@PathVariable("id") Long id, Model model) {
        Invoice invoice = invoiceService.getOdrerId(id);
        model.addAttribute("invoice", invoice);
        return "Admin/Orderedit";
    }
    @PostMapping("/edit/{id}")
    public String editproduct(@PathVariable("id") Long id, @ModelAttribute("invoice1") Invoice invoice1 ) {
        Invoice invoice = invoiceService.getOdrerId(id);


        invoice.setStatus(invoice1.getStatus());


        invoiceService.updateBook(invoice );
        return "redirect:/admin/Order";
    }
    @PostMapping("/edit1/{id}")
    public String editproduct1(@PathVariable("id") Long id) {
        Invoice invoice = invoiceService.getOdrerId(id);
        boolean currentStatus = invoice.getStatus();
        invoice.setStatus(!currentStatus);
        invoiceService.updateBook(invoice);

        return "redirect:/admin/Order";
    }



}
