package com.Tutienda.controller.web;

import com.Tutienda.entity.*;
import com.Tutienda.entity.product.Clothing;
import com.Tutienda.entity.product.Product;
import com.Tutienda.service.IClothingSizeService;
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
public class ClothingController {
    private final IProductService iProductService;
    private final IClothingSizeService IClothingSizeService;
    private final IUploadFileService iUploadFileService;

    public ClothingController(com.Tutienda.service.IClothingSizeService IClothingSizeService, IProductService iProductService, IUploadFileService iUploadFileService) {
        this.IClothingSizeService = IClothingSizeService;
        this.iProductService = iProductService;
        this.iUploadFileService = iUploadFileService;
    }

    @GetMapping("/agregar/ropa")
    public String showAddClothingForm(Model model) {
        model.addAttribute("title", "Añadir ropa");
        model.addAttribute("sizes", ClothingSizeEnum.values());
        model.addAttribute("types", ClothingTypeEnum.values());
        model.addAttribute("genders", Gender.values());
        model.addAttribute("clothing", new Clothing());
        return "products/add_clothing_form";
    }

    @PostMapping("/agregar/ropa")
    public String saveClothing(@ModelAttribute("clothing") Clothing clothing, Model model,
                               @RequestParam("images") List<MultipartFile> images,
                               @RequestParam(name = "sizes", required = false) List<ClothingSizeEnum> sizes,
                               @RequestParam(name = "quantities", required = false) List<Integer> quantities,
                               @RequestParam(name = "imag",required = false) MultipartFile imag
    ) throws IOException {

        if(sizes == null || sizes.isEmpty() || quantities == null || quantities.isEmpty()) {
            return "redirect:/tienda";
        }
        List<ClothingSize> clothingSizes = IClothingSizeService.getAllClothingSizes(sizes);
        clothing.setClothingSizes(clothingSizes);

        clothing.setStockSizeXSmall(quantities.get(0));
        clothing.setStockSizeSmall(quantities.get(1));
        clothing.setStockSizeMedium(quantities.get(2));
        clothing.setStockSizeLarge(quantities.get(3));
        clothing.setStockSizeXLarge(quantities.get(4));
        clothing.setStockSizeXXLarge(quantities.get(5));
        if (imag != null && !imag.isEmpty()) {
            clothing.setImagePrimary(iUploadFileService.save(imag).getUrl());
        }
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
            clothing.setImageUrl(imageUrls);
        }
        Product productResponse = iProductService.save(clothing);
        return "redirect:/producto/"+ productResponse.getId();
    }
}
