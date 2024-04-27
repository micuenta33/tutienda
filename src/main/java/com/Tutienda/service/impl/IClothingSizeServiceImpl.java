package com.Tutienda.service.impl;

import com.Tutienda.entity.ClothingSize;
import com.Tutienda.entity.ClothingSizeEnum;
import com.Tutienda.repository.IClothingSizeRepository;
import com.Tutienda.service.IClothingSizeService;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class IClothingSizeServiceImpl implements IClothingSizeService {

    private final IClothingSizeRepository IClothingSizeRepository;

    public IClothingSizeServiceImpl(IClothingSizeRepository IClothingSizeRepository) {
        this.IClothingSizeRepository = IClothingSizeRepository;
    }

    @Override
    public List<ClothingSize> getAllClothingSizes(List<ClothingSizeEnum> clothingSizes) {
        return IClothingSizeRepository.findAllByClothingSizeIn(clothingSizes);
    }


}
