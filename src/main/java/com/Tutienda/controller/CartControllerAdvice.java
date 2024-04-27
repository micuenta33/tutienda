package com.Tutienda.controller;

import com.Tutienda.dto.Cart;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;
@ControllerAdvice
public class CartControllerAdvice {

    private Cart cart;

    public CartControllerAdvice(Cart cart) {
        this.cart = cart;
    }

    @ModelAttribute
    public void addCartToModel(Model model) {
        model.addAttribute("cart", cart);
    }
}
