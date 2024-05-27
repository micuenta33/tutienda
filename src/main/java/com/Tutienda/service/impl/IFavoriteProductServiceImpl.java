package com.Tutienda.service.impl;

import com.Tutienda.entity.FavoriteProduct;
import com.Tutienda.repository.IFavoriteProductRepository;
import com.Tutienda.service.IFavoriteProductService;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class IFavoriteProductServiceImpl implements IFavoriteProductService {

    private final IFavoriteProductRepository iFavoriteProductRepository;

    public IFavoriteProductServiceImpl(IFavoriteProductRepository iFavoriteProductRepository) {
        this.iFavoriteProductRepository = iFavoriteProductRepository;
    }


    @Override
    public void save(FavoriteProduct favoriteProduct) {
        iFavoriteProductRepository.save(favoriteProduct);
    }

    @Override
    public void delete(Long id) {
        iFavoriteProductRepository.deleteById(id);
    }

    @Override
    public List<FavoriteProduct> findAllByUserId(Long userId) {
        return iFavoriteProductRepository.findAllByUserId(userId);
    }
}
