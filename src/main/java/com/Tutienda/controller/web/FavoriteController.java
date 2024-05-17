package com.Tutienda.controller.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class FavoriteController {

    @GetMapping("/favoritos")
    public String getFavorite(Model model) {
        return "products-favorite";
    }
}
