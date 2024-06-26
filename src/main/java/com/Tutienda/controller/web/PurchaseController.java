package com.Tutienda.controller.web;

import com.Tutienda.entity.Purchase;
import com.Tutienda.entity.users.User;
import com.Tutienda.service.IPurchaseService;
import com.Tutienda.service.IRatingService;
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
    private final IUserService userService;
    private final IRatingService ratingService;

    public PurchaseController(IPurchaseService purchaseService, IUserService userService, IRatingService ratingService) {
        this.purchaseService = purchaseService;
        this.userService = userService;
        this.ratingService = ratingService;
    }

    @GetMapping("/cart")
    public String getCart(Model model) {
        model.addAttribute("purchase", new Purchase());
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.isAuthenticated() && !authentication.getPrincipal().equals("anonymousUser")) {
            UserDetails userDetails = (UserDetails) authentication.getPrincipal();
            String username = userDetails.getUsername();
            userService.getUserByUsername(username).ifPresent(user ->model.addAttribute("user", user));
        }
        return "cart";
    }
    @GetMapping("/purchases")
    public String getPurchase(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.isAuthenticated() && !authentication.getPrincipal().equals("anonymousUser")) {
            UserDetails userDetails = (UserDetails) authentication.getPrincipal();
            String username = userDetails.getUsername();
            User user = userService.getUserByUsername(username).orElseThrow();
            model.addAttribute("user",user);
            model.addAttribute("purchases",purchaseService.getPurchaseByUser(user));
        }
        return "purchases";
    }
    @PostMapping("/purchase")
    public ResponseEntity<?> purchase(@RequestBody Purchase purchase) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.isAuthenticated() && !authentication.getPrincipal().equals("anonymousUser")) {
            UserDetails userDetails = (UserDetails) authentication.getPrincipal();
            String username = userDetails.getUsername();
           if (purchaseService.savePurchase(purchase, username)) {
               return ResponseEntity.ok("ok");
           }
        }
        return ResponseEntity.badRequest().build();
    }

    @GetMapping("/purchases/{id}")
    public String getPurchaseByUser(@PathVariable Long id, Model model) {
        Purchase purchase = purchaseService.getPurchaseById(id);
        User user = userService.getUserById(purchase.getUser().getId()).orElseThrow();

        // Asume que purchaseService.getPurchaseById(id) y userService.getUserById(id) ya están implementados
        purchase.getItems().forEach(item -> {
            boolean hasRated = ratingService.hasRated(user, item.getShoe());
            item.setHasRated(hasRated); // Supone que has agregado este campo en el item
            System.out.println(item);
        });
        model.addAttribute("purchase", purchase);
        model.addAttribute("user", user);
        return "/purchases_details";
    }

    @GetMapping("/invoice/pdf/{id}")
    public String getInvoice(@PathVariable Long id, Model model) {
        // Obtener la compra por id (simulado aquí)
        Purchase purchase = purchaseService.getPurchaseById(id);
        // Añadir la compra al modelo
        model.addAttribute("purchase", purchase);
        return "invoice/pdf";
    }


}