package com.Tutienda.controller.web;

import com.Tutienda.entity.users.Address;
import com.Tutienda.entity.users.User;
import com.Tutienda.service.IUserService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Controller
public class UserController {

    private final IUserService userService;

    public UserController(IUserService userService) {
        this.userService = userService;
    }

    @GetMapping("/perfil")
    public String getUser(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.isAuthenticated() && !authentication.getPrincipal().equals("anonymousUser")) {
            UserDetails userDetails = (UserDetails) authentication.getPrincipal();
            String username = userDetails.getUsername();
            model.addAttribute("user", userService.getUserByUsername(username).orElseThrow());
        }
        model.addAttribute("address",new Address());
        return "users/user";
    }

    @PostMapping("/perfil")
    public String updateUser(@ModelAttribute("user") User user, Model model) {
        userService.updateUser(user);
        return "redirect:/tienda";
    }
    @PostMapping("/perfil/direccion")
    public String updateAddressUser(@ModelAttribute("address") Address address,@RequestParam("userId") Long userId, Model model) {
        userService.updateAddressUser(address,userId);
        return "redirect:/perfil";
    }



}
