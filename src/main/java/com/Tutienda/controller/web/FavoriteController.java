package com.Tutienda.controller.web;

import com.Tutienda.entity.FavoriteProduct;
import com.Tutienda.entity.users.User;
import com.Tutienda.service.IFavoriteProductService;
import com.Tutienda.service.IUserService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class FavoriteController {

    private final IFavoriteProductService iFavoriteProductService;
    private final IUserService userService;

    public FavoriteController(IFavoriteProductService iFavoriteProductService, IUserService userService) {
        this.iFavoriteProductService = iFavoriteProductService;
        this.userService = userService;
    }
    @GetMapping("/favoritos")
    public String getFavorite(Model model) {
        User user =  userService.getUserByUsername(SecurityContextHolder.getContext().getAuthentication().getName()).orElseThrow();
        List<FavoriteProduct> favoriteProduct =  iFavoriteProductService.findAllByUserId(user.getId());
        model.addAttribute("favoriteProduct",favoriteProduct);
        return "products-favorite";
    }

    @PostMapping("/favoritos")
    public ResponseEntity<?> addFavorite(@RequestBody FavoriteProduct favoriteProduct) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.isAuthenticated() && !authentication.getPrincipal().equals("anonymousUser")) {
            UserDetails userDetails = (UserDetails) authentication.getPrincipal();
            String username = userDetails.getUsername();
            User user = userService.getUserByUsername(username).orElseThrow();
            favoriteProduct.setUser(user);
            iFavoriteProductService.save(favoriteProduct);
            return ResponseEntity.ok("ok");
        }
        return ResponseEntity.badRequest().build();
    }
    @DeleteMapping("/favoritos/{id}")
    public ResponseEntity<?> deleteFavorite(@PathVariable Long id) {
           iFavoriteProductService.delete(id);
        return ResponseEntity.ok("ok");
    }



}
