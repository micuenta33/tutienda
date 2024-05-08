package com.Tutienda.controller.web;

import com.Tutienda.entity.enums.Gender;
import com.Tutienda.entity.ImageUrl;
import com.Tutienda.entity.enums.WatchTypeEnum;
import com.Tutienda.entity.product.Product;
import com.Tutienda.entity.product.Watch;
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
public class WatchController {
    private final IProductService iProductService;
    private final IUploadFileService iUploadFileService;

    public WatchController(IProductService iProductService, IUploadFileService iUploadFileService) {
        this.iProductService = iProductService;
        this.iUploadFileService = iUploadFileService;
    }

    @GetMapping("/agregar/reloje")
    public String addProducts(Model model) {
        model.addAttribute("title", "Añadir reloje");
        model.addAttribute("genders", Gender.values());
        model.addAttribute("types", WatchTypeEnum.values());
        model.addAttribute("watch", new Watch());
        return "products/add_watches_form";
    }
    @PostMapping("/agregar/reloje")
    public String saveProducts(@ModelAttribute("watch") Watch watch, Model model,
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
            watch.setImagePrimary(iUploadFileService.save(imag).getUrl());
            watch.setImageUrl(imageUrls);
        }
        Product productResult = iProductService.save(watch);
        return "redirect:/producto/" + productResult.getId();
    }
}
