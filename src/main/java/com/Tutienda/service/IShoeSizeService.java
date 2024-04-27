package com.Tutienda.service;


import com.Tutienda.entity.ShoeSize;
import com.Tutienda.entity.ShoeSizeEnum;

import java.util.List;

public interface IShoeSizeService {
    List<ShoeSize> getAllShoeSizes(List<ShoeSizeEnum> shoeSizes);
}
