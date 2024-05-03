package com.Tutienda.controller.web;

import com.Tutienda.entity.*;
import com.Tutienda.entity.product.Product;
import com.Tutienda.entity.product.Shoe;
import com.Tutienda.service.IProductService;
import com.Tutienda.service.IShoeSizeService;
import com.Tutienda.service.IUploadFileService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Controller
public class ShoeController {
    private final IProductService iProductService;
    private final IShoeSizeService iShoeSizeService;
    private final IUploadFileService iUploadFileService;

    public ShoeController(IProductService iProductService, IShoeSizeService iShoeSizeService, IUploadFileService iUploadFileService) {
        this.iProductService = iProductService;
        this.iShoeSizeService = iShoeSizeService;
        this.iUploadFileService = iUploadFileService;
    }

    @GetMapping("/agregar/zapatos")
    public String showAddShoeForm(Model model) {
        model.addAttribute("title", "Añadir zapatos");
        model.addAttribute("types", ShoeTypeEnum.values());
        model.addAttribute("sizes", ShoeSizeEnum.values());
        model.addAttribute("genders", Gender.values());
        model.addAttribute("shoe", new Shoe());
        return "products/add_shoe_form";
    }


    @PostMapping("/agregar/zapatos")
    public String saveShoe(@ModelAttribute("shoe") Shoe shoe, Model model,
                           @RequestParam("images") List<MultipartFile> images,
                           @RequestParam(name = "sizes", required = false) List<ShoeSizeEnum> sizes,
                           @RequestParam(name = "quantities", required = false) List<Integer> quantities,
                           @RequestParam(name = "imag",required = false) MultipartFile imag
    ) throws IOException {

        if(sizes == null || sizes.isEmpty() || quantities == null || quantities.isEmpty()) {
            return "redirect:/tienda";
        }
        List<ShoeSize> ShoeSizes = iShoeSizeService.getAllShoeSizes(sizes);
        shoe.setShoeSizes(ShoeSizes);
        shoe.setStockSize35(quantities.get(0));
        shoe.setStockSize36(quantities.get(1));
        shoe.setStockSize37(quantities.get(2));
        shoe.setStockSize38(quantities.get(3));
        shoe.setStockSize39(quantities.get(4));
        shoe.setStockSize40(quantities.get(5));
        shoe.setStockSize41(quantities.get(6));
        shoe.setStockSize42(quantities.get(7));
        shoe.setStockSize43(quantities.get(8));
        shoe.setStockSize44(quantities.get(9));
        shoe.setStockSize45(quantities.get(10));
        shoe.setStockSize46(quantities.get(11));

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
            shoe.setImagePrimary(iUploadFileService.save(imag).getUrl());
            shoe.setImageUrl(imageUrls);
        }
        Product productResponse = iProductService.save(shoe);
        return "redirect:/producto/"+ productResponse.getId();
    }

}
