package com.Tutienda.controller.web;



import com.Tutienda.entity.enums.Gender;
import com.Tutienda.entity.ImageUrl;
import com.Tutienda.entity.product.*;

import com.Tutienda.service.IProductService;
import com.Tutienda.service.IUploadFileService;
import com.Tutienda.util.paginator.PageRender;

import com.Tutienda.util.paginator.PageUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

import java.util.*;
import java.util.stream.Collectors;


@Controller
public class ProductController {
    private final IProductService iProductService;
    private final IUploadFileService iUploadFileService;

    public ProductController(IProductService iProductService, IUploadFileService iUploadFileService) {
        this.iProductService = iProductService;
        this.iUploadFileService = iUploadFileService;
    }

    @GetMapping("/tienda")
    public String getShop(@RequestParam(name = "page", defaultValue = "0") int page,
                          @RequestParam(name = "category", required = false) String category,
                          @RequestParam(name = "gender", required = false) String gender,
                          @RequestParam(name = "type", required = false) String type,
                          Model model) {

        Page<Product> productListPage = iProductService.findFilteredAndPaginatedProducts(page, category, gender,type, 9);
        String[] queryParams = buildQueryParams(category, gender);
        PageRender<Product> pageRender = new PageRender<>("/tienda", productListPage, queryParams);
        model.addAttribute("products", productListPage.getContent());
        model.addAttribute("page", pageRender);
        model.addAttribute("title", "Tienda");
        return "shop";
    }

    private String[] buildQueryParams(String categoria, String gender) {
        List<String> queryParams = new ArrayList<>();
        if (categoria != null && !categoria.isEmpty()) {
            queryParams.add("categoria=" + categoria);
        }
        if (gender != null && !gender.isEmpty()) {
            queryParams.add("gender=" + gender);
        }
        return queryParams.isEmpty() ? null : queryParams.toArray(new String[0]);
    }

    @GetMapping("/producto/{id}")
    public String getProduct(@PathVariable Long id, Model model) {
        Optional<Product> productOptional = iProductService.findById(id);
        if (productOptional.isEmpty()) {
            return "redirect:/tienda";
        }
        Product product = productOptional.get();

        model.addAttribute("product", product);
        model.addAttribute("products", iProductService.findAll());
        return "shop-single";
    }

    @GetMapping("/agregar/otros")
    public String addProducts(Model model) {
        model.addAttribute("title", "Añadir otros productos");
        model.addAttribute("genders", Gender.values());
        model.addAttribute("product", new Product());
        return "products/add_product_from";
    }
    @PostMapping("/agregar/otros")
    public String saveProducts(@ModelAttribute("product") Product product, Model model,
                               @RequestParam("images") List<MultipartFile> images,
                               @RequestParam(name = "imag",required = false) MultipartFile imag) throws IOException {

        // Check if images are uploaded
        if (imag != null && !imag.isEmpty() || images != null && !images.isEmpty()) {
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
            product.setImagePrimary(iUploadFileService.save(imag).getUrl());
            product.setImageUrl(imageUrls);
        }
        Product productResult = iProductService.save(product);
        return "redirect:/producto/" + productResult.getId();
    }

    @GetMapping(value ="/product/name/{term}" ,produces = {"application/json"})
    public @ResponseBody List<Product> getProductsByName(@PathVariable String term) {
        return iProductService.findByName(term);
    }

}
