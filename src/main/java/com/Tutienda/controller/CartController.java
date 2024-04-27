package com.Tutienda.controller;

import com.Tutienda.dto.CarItem;
import com.Tutienda.dto.Cart;
import com.Tutienda.entity.product.Product;
import com.Tutienda.service.IProductService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class CartController {

    private IProductService productService;
    private Cart cart;

    public CartController(IProductService productService, Cart cart) {
        this.productService = productService;
        this.cart = cart;
    }

    @GetMapping("/cart")
    public String obtenerProductosDelCarrito(Model model) {
        model.addAttribute("cart", cart);
        return "cart";
    }


//    @PostMapping("/add")
//        public String addToCart(@RequestParam Long productId, Model model) {
//        Optional<Product> product = productService.findById(productId);
//
//        if (product.isPresent()) {
//            CarItem existingItem = findCartItemByProduct(product.get());
//            System.out.println(product.get());
//            if (existingItem != null) {
//                // Si el producto ya está en el carrito, incrementar la cantidad
//                existingItem.setQuantity(existingItem.getQuantity() + 1);
//            } else {
//                // Si el producto no está en el carrito, agregarlo como nuevo CarItem
//                CarItem newItem = new CarItem(product.get(), 1, product.get().getPrice());
//                cart.getCarItems().add(newItem);
//                // Actualizar la cantidad total de productos en el carrito
//                cart.setQuantityProduct(cart.getQuantityProduct() + 1);
//            }
//        }
//        return "redirect:/cart";
//    }

    @PostMapping("/add")
    public ResponseEntity<?> addToCart(@RequestBody Product product) {

        CarItem existingItem = findCartItemByProduct(product);

        if (existingItem != null) {
            // Si el producto ya está en el carrito, incrementar la cantidad
            existingItem.setQuantity(existingItem.getQuantity() + 1);
        } else {
            // Si el producto no está en el carrito, agregarlo como nuevo CarItem
            CarItem newItem = new CarItem(product, 1, product.getPrice());
            cart.getCarItems().add(newItem);
            // Actualizar la cantidad total de productos en el carrito
            cart.setQuantityProduct(cart.getQuantityProduct() + 1);
        }
        return ResponseEntity.ok(cart);
    }

    // Método de utilidad para buscar un CarItem por producto dentro del carrito
    private CarItem findCartItemByProduct(Product product) {
        for (CarItem item : cart.getCarItems()) {
            if (item.getProduct().getId() == product.getId()) {
                return item;
            }
        }
        return null;
    }
}