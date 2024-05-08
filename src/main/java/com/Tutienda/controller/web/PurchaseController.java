package com.Tutienda.controller.web;

import com.Tutienda.entity.Item;
import com.Tutienda.entity.Purchase;
import com.Tutienda.entity.users.User;
import com.Tutienda.repository.IItemRepository;
import com.Tutienda.repository.IPurchaseRepository;
import com.Tutienda.service.IProductService;
import com.Tutienda.service.IUserService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
public class PurchaseController {

    private IProductService productService;
    private IUserService userService;
    private IPurchaseRepository purchaseRepository;
    private IItemRepository itemRepository;

    public PurchaseController(IProductService productService, IPurchaseRepository purchaseRepository, IItemRepository itemRepository, IUserService userService) {
        this.productService = productService;
        this.purchaseRepository = purchaseRepository;
        this.itemRepository = itemRepository;
        this.userService = userService;
    }

    @GetMapping("/cart")
    public String getCart(Model model) {
        model.addAttribute("purchase", new Purchase());
        return "cart";
    }

    @PostMapping("/purchase")
    public ResponseEntity<?> purchase(@RequestBody Purchase purchase) {
        System.out.println(purchase);
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.isAuthenticated() && !authentication.getPrincipal().equals("anonymousUser")) {
            UserDetails userDetails = (UserDetails) authentication.getPrincipal();
            String username = userDetails.getUsername();

            Optional<User> user = userService.getUserByUsername(username);

            if (user.isPresent()) {
                purchase.setUser(user.get());
                purchase.setDate(purchase.getDate());
                purchase.setTotalPurchase(purchase.getTotalPurchase());
                Purchase savedPurchase =purchaseRepository.save(purchase);
                for (Item item : savedPurchase.getItems()) {
                    item.setPurchase(savedPurchase);
                    itemRepository.save(item);
                }
            }
        }
        return ResponseEntity.ok("ok");
    }
}