package com.Tutienda.controller.web;

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

    @GetMapping(value = {"/página-principal","/"})
    public String index(Model model) {
        model.addAttribute("products", productService.findAllByBestRating(4));
        return "index";
    }
    @GetMapping("/sobre-nosotros")
    public String about() {
        return "otherpages/about";
    }

    @GetMapping("/contacto")
    public String contact() {
        return "otherpages/contact";
    }
}
