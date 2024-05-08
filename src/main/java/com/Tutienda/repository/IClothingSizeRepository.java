package com.Tutienda.repository;

import com.Tutienda.entity.ClothingSize;
import com.Tutienda.entity.enums.ClothingSizeEnum;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IClothingSizeRepository extends JpaRepository<ClothingSize, Long> {

    List<ClothingSize> findAllByClothingSizeIn(List<ClothingSizeEnum> clothingSizes);

}
