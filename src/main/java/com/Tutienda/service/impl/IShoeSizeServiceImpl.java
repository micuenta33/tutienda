package com.Tutienda.service.impl;


import com.Tutienda.entity.ShoeSize;
import com.Tutienda.entity.ShoeSizeEnum;
import com.Tutienda.repository.IShoeSizeRepository;
import com.Tutienda.service.IShoeSizeService;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class IShoeSizeServiceImpl implements IShoeSizeService {
    private final IShoeSizeRepository iShoeSizeRepository;

    public IShoeSizeServiceImpl(IShoeSizeRepository iShoeSizeRepository) {
        this.iShoeSizeRepository = iShoeSizeRepository;
    }

    @Override
    public List<ShoeSize> getAllShoeSizes(List<ShoeSizeEnum> shoeSizes) {
        return iShoeSizeRepository.findAllByShoeSizeIn(shoeSizes);
    }
}
