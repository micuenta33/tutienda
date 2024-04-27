package com.Tutienda.controller;



import com.Tutienda.entity.Gender;
import com.Tutienda.entity.ImageUrl;
import com.Tutienda.entity.product.Clothing;
import com.Tutienda.entity.product.Product;
import com.Tutienda.entity.ClothingSizeEnum;
import com.Tutienda.entity.product.Shoe;

import com.Tutienda.service.IImageUrlService;
import com.Tutienda.service.IProductService;
import com.Tutienda.service.IUploadFileService;
import com.Tutienda.util.paginator.PageRender;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

import java.util.*;


@Controller
public class ProductController {
    private final IProductService iProductService;
    private final IUploadFileService iUploadFileService;

    public ProductController(IProductService iProductService, IUploadFileService iUploadFileService) {
        this.iProductService = iProductService;
        this.iUploadFileService = iUploadFileService;
    }

    @GetMapping("/shop")
    public String getShop(@RequestParam(name = "page", defaultValue = "0") int page, Model model) {
        Pageable pageRequest = PageRequest.of(page, 9);
        Page<Product> productList = iProductService.findAll(pageRequest);
        // Recorrer la lista de productos y cargar explícitamente la información de las tallas
        productList.forEach(product -> {
            if (product instanceof Clothing) {
                Clothing clothing = (Clothing) product;
                clothing.getClothingSizes().size(); // Cargar tallas de ropa
            } else if (product instanceof Shoe) {
                Shoe shoe = (Shoe) product;
                shoe.getShoeSizes().size(); // Cargar tallas de zapatos
            }
        });
        PageRender<Product> pageRender = new PageRender<>("/shop", productList);
        model.addAttribute("products",productList);
        model.addAttribute("page", pageRender);
        model.addAttribute("title", "Shop");
        return "shop";
    }

    @GetMapping("/shop-single/{id}")
    public String getProduct(@PathVariable Long id, Model model) {
        Optional<Product> productOptional = iProductService.findById(id);
        if (productOptional.isEmpty()) {
            return "redirect:/shop";
        }
        Product product = productOptional.get();
        if (product instanceof Clothing) {
            Clothing clothing = (Clothing) product;
            clothing.getClothingSizes().size(); // Cargar tallas de ropa
            model.addAttribute("clothing", clothing);
            System.out.println(clothing);
        } else if (product instanceof Shoe) {
            Shoe shoe = (Shoe) product;
            shoe.getShoeSizes().size(); // Cargar tallas de zapatos
            model.addAttribute("shoe", shoe);
            System.out.println(shoe);
        }
        model.addAttribute("product", product);
        model.addAttribute("products", iProductService.findAll());
        return "shop-single";
    }

    @GetMapping("/product/add")
    public String addProducts(Model model) {
        model.addAttribute("genders", Gender.values());
        model.addAttribute("product", new Product());
        return "add_product_from";
    }
    @PostMapping("/product/add")
    public String saveProducts(@ModelAttribute("product") Product product, Model model,
                               @RequestParam("images") List<MultipartFile> images) {

        // Check if images are uploaded
        if (images != null && !images.isEmpty()) {
            List<ImageUrl> imageUrls = new ArrayList<>();

            // Process each image
            for (MultipartFile image : images) {
                try {
                    // Save each image to the server
                    imageUrls.add(iUploadFileService.save(image));
                } catch (IOException e) {
                    // Handle exception if image saving fails
                    e.printStackTrace();
                    // You can choose to throw an exception or handle it gracefully
                }
            }
            // Set the image URLs in the product
            product.setImageUrl(imageUrls);
        }
        Product productResult = iProductService.save(product);
        return "redirect:/shop-single/" + productResult.getId();
    }

}
