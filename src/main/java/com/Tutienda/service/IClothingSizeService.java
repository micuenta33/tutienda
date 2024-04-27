package com.Tutienda.service;

import com.Tutienda.entity.ClothingSize;
import com.Tutienda.entity.ClothingSizeEnum;

import java.util.List;

public interface IClothingSizeService {
    List<ClothingSize> getAllClothingSizes(List<ClothingSizeEnum> clothingSizes);
}
