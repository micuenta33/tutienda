package com.Tutienda.controller.web;

import com.Tutienda.entity.enums.Gender;
import com.Tutienda.entity.ImageUrl;
import com.Tutienda.entity.enums.UVProtectionLevelEnum;
import com.Tutienda.entity.product.Glass;
import com.Tutienda.entity.product.Product;
import com.Tutienda.service.IProductService;
import com.Tutienda.service.IUploadFileService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Controller
public class GlassController {

    private final IProductService iProductService;
    private final IUploadFileService iUploadFileService;

    public GlassController(IProductService iProductService, IUploadFileService iUploadFileService) {
        this.iProductService = iProductService;
        this.iUploadFileService = iUploadFileService;
    }

    @GetMapping("/agregar/gafas")
    public String addProducts(Model model) {
        model.addAttribute("title", "Añadir gafas");
        model.addAttribute("UVProtectionLevelEnumList", UVProtectionLevelEnum.values());
        model.addAttribute("genders", Gender.values());
        model.addAttribute("glass", new Glass());
        return "products/add_glasses_form";
    }
    @PostMapping("/agregar/gafas")
    public String saveProducts(@ModelAttribute("glass") Glass glass, Model model,
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
            glass.setImagePrimary(iUploadFileService.save(imag).getUrl());
            glass.setImageUrl(imageUrls);
        }
        Product productResult = iProductService.save(glass);
        return "redirect:/producto/" + productResult.getId();
    }
}
