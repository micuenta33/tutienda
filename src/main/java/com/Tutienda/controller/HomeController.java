package com.Tutienda.controller;

import com.Tutienda.service.IProductService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    private final IProductService productService;

    public HomeController(IProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/index")
    public String index(Model model) {
        model.addAttribute("size", productService.findAllByBestRating(4).size());
        model.addAttribute("products", productService.findAllByBestRating(4));
        return "index";
    }

}
