package com.Tutienda.service;


import com.Tutienda.entity.Size;
import com.Tutienda.entity.enums.ShoeSizeEnum;

import java.util.List;

public interface IShoeSizeService {
    List<Size> getAllShoeSizes(List<ShoeSizeEnum> shoeSizes);

}
