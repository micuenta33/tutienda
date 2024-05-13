package com.Tutienda.controller.web;

import com.Tutienda.entity.Item;
import com.Tutienda.entity.Purchase;
import com.Tutienda.entity.users.User;
import com.Tutienda.repository.IItemRepository;
import com.Tutienda.repository.IPurchaseRepository;
import com.Tutienda.service.IProductService;
import com.Tutienda.service.IPurchaseService;
import com.Tutienda.service.IUserService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;



@Controller
public class PurchaseController {

    private final IPurchaseService purchaseService;
;

    public PurchaseController(IPurchaseService purchaseService) {
        this.purchaseService = purchaseService;
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
            purchaseService.savePurchase(purchase, username);
        }
        return ResponseEntity.ok("ok");
    }
}