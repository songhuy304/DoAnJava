package com.example.doanjava.Controller;

import com.example.doanjava.daos.OrderViewModel;
import com.example.doanjava.daos.Cart;
import com.example.doanjava.daos.Item;
import com.example.doanjava.services.CartService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.antlr.v4.runtime.misc.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/cart")
@RequiredArgsConstructor
public class CartController {

    private final CartService cartService;
    private JavaMailSender javaMailSender;

    @Autowired
    public CartController(CartService cartService, JavaMailSender javaMailSender) {
        this.cartService = cartService;
        this.javaMailSender = javaMailSender;
    }
    @GetMapping
    public String showCart(HttpSession session,
                           @NotNull Model model) {
        model.addAttribute("cart", cartService.getCart(session));
        model.addAttribute("totalPrice",
                cartService.getSumPrice(session));
        model.addAttribute("totalQuantity",
                cartService.getSumQuantity(session));
        return "product/cart";
    }
    @GetMapping("/removeFromCart/{id}")
    public String removeFromCart(HttpSession session,
                                 @PathVariable Long id) {
        var cart = cartService.getCart(session);
        cart.removeItems(id);
        return "redirect:/cart";
    }
    @GetMapping("/updateCart/{id}/{quantity}")
    public String updateCart(HttpSession session,
                             @PathVariable Long id,
                             @PathVariable int quantity) {
        var cart = cartService.getCart(session);
        cart.updateItems(id, quantity);
        return "Cart updated successfully";
    }
    @GetMapping("/clearCart")
    public String clearCart(HttpSession session) {
        cartService.removeCart(session);
        return "redirect:/cart ";
    }
    @GetMapping("/thanhtoan")
    public String thanhtoan(HttpSession session, @ModelAttribute("ord") OrderViewModel ord) {
        cartService.saveCart(session, ord);
        return "product/cart";
    }
    @GetMapping("/checkout")
    public String checkout(HttpSession session, @ModelAttribute("ord") OrderViewModel ord , Model model)
    {
        session.setAttribute("ord", ord);
        Date currentDate = new Date();
        DecimalFormat decimalFormat = new DecimalFormat("#,### VND");
        String formattedTotalAmount = decimalFormat.format(cartService.getSumPrice(session));
        Cart cart = cartService.getCart(session);
        List<Item> cartItems = cart.getCartItems();
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        String formattedDate = dateFormat.format(currentDate);
        String to = ord.getEmail(); // Địa chỉ email của người nhận
        String subject = "Xác Nhận Đơn hàng";
        String body = "Đơn Hàng Của Bạn Được Đặt Thành Công\n";
        body += "-------------------------------------------------" + "\n";
        body += "|Người Nhận: " + ord.getCustomerName() + "|"+ "\n";
        body += "|Địa Chỉ Người Nhận: " + ord.getAddress() + "|"+"\n";
        body += "|Người Gửi: Kim Jong Huy" +"|"+ "\n";
        body += "|Chi Tiết Sản Phẩm" +"|"+ "\n";

        for (Item cartItem : cartItems) {
            DecimalFormat decimalFormat1 = new DecimalFormat("#,### VND");
            String formattedTotalAmount1 = decimalFormat1.format(cartItem.getQuantity() * cartItem.getPrice());
            body += "|Tên Sản Phẩm: " + cartItem.getBookName()  + "  | "+ "Số Lượng : "+ cartItem.getQuantity() +"Thành Tiền : " + formattedTotalAmount1 ;
            body += "\n";
        }
        body += "-------------------------------------------------";
        body += "|Tổng tiền: " + formattedTotalAmount + "|"+"\n";
        body += "|Ngày gửi: " + formattedDate  + "|"+"\n";
        sendEmail(to, subject, body);
        cartService.saveCart(session, ord);
        return "redirect:/cart";

    }
    private void sendEmail(String to, String subject, String body) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(to);

        message.setSubject(subject);
        message.setText(body);
        javaMailSender.send(message);
    }

}
