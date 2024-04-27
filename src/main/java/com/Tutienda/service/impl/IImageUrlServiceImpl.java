package com.Tutienda.service.impl;

import com.Tutienda.entity.ImageUrl;
import com.Tutienda.service.IImageUrlService;
import org.springframework.stereotype.Service;

@Service
public class IImageUrlServiceImpl implements IImageUrlService {
    @Override
    public ImageUrl save(ImageUrl imageUrl) {
        return imageUrl;
    }
}
