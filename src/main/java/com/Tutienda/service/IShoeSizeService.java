package com.Tutienda.service;


import com.Tutienda.entity.ShoeSize;
import com.Tutienda.entity.enums.ShoeSizeEnum;

import java.util.List;

public interface IShoeSizeService {
    List<ShoeSize> getAllShoeSizes(List<ShoeSizeEnum> shoeSizes);
}
