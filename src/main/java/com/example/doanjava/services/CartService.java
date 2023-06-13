package com.example.doanjava.services;

import com.example.doanjava.daos.Cart;
import com.example.doanjava.daos.Item;
import com.example.doanjava.daos.OrderViewModel;
import com.example.doanjava.entity.Invoice;
import com.example.doanjava.entity.ItemInvoice;
import com.example.doanjava.entity.User;
import com.example.doanjava.repository.IInvoiceRepository;
import com.example.doanjava.repository.IItemInvoiceRepository;
import com.example.doanjava.repository.IProductRepository;
import com.example.doanjava.repository.IUserRepository;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.antlr.v4.runtime.misc.NotNull;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.Optional;
import java.util.Random;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
@Service
@RequiredArgsConstructor
@Transactional(isolation = Isolation.SERIALIZABLE,
        rollbackFor = {Exception.class, Throwable.class})

public class CartService {
    private final IInvoiceRepository invoiceRepository;
    private final IItemInvoiceRepository itemInvoiceRepository;
    private final IProductRepository productRepositoryRepository;
    private final IUserRepository iUserRepositoryRepository;
    private static final String CART_SESSION_KEY = "cart";
    public Cart getCart(@NotNull HttpSession session) {
        return Optional.ofNullable((Cart)
                        session.getAttribute(CART_SESSION_KEY))
                .orElseGet(() -> {
                    Cart cart = new Cart();
                    session.setAttribute(CART_SESSION_KEY, cart);
                    return cart;
                });
    }
    public void updateCart(@NotNull HttpSession session, Cart cart) {
        session.setAttribute(CART_SESSION_KEY, cart);
    }
    public void removeCart(@NotNull HttpSession session) {
        session.removeAttribute(CART_SESSION_KEY);
    }
    public int getSumQuantity(@NotNull HttpSession session) {
        return getCart(session).getCartItems().stream()
                .mapToInt(Item::getQuantity)
                .sum();
    }
    public double getSumPrice(@NotNull HttpSession session) {
        return getCart(session).getCartItems().stream()
                .mapToDouble(item -> item.getPrice() *
                        item.getQuantity())
                .sum();
    }
    public void saveCart(@NotNull HttpSession session , OrderViewModel ord) {
        var cart = getCart(session);
        if (cart.getCartItems().isEmpty()) return;
        var invoice = new Invoice();
        invoice.setInvoiceDate(new Date(new Date().getTime()));
        invoice.setPrice(getSumPrice(session));
        invoice.setCustomerName(ord.getCustomerName());
        invoice.setAddress(ord.getAddress());
        invoice.setPhone(ord.getPhone());
            invoice.setMaDH(generateOrderCode());
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            if (authentication != null && authentication.isAuthenticated()) {
                String currentUserName = authentication.getName();


                User currentUser = iUserRepositoryRepository.findByUserName(currentUserName);

                if (currentUser != null) {
                    invoice.setUser(currentUser);
                    invoice.setUserId(currentUser.getId());
                }
            }
        invoice.setTypePayment(ord.getTypePayment());
        invoiceRepository.save(invoice);
        cart.getCartItems().forEach(item -> {
            var items = new ItemInvoice();
            items.setInvoice(invoice);
            items.setQuantity(item.getQuantity());
            items.setPrice(item.getPrice());
            items.setProduct(productRepositoryRepository.findById(item.getBookId())
                            .orElseThrow());
            itemInvoiceRepository.save(items);
        });
        removeCart(session);
    }
    private String generateOrderCode() {
        // Generate a random number between 1000 and 9999
        Random random = new Random();
        int randomNumber = random.nextInt(9000) + 1000;

        // Create the order code using the generated random number
        String orderCode = "DH" + randomNumber;

        return orderCode;
    }
}
