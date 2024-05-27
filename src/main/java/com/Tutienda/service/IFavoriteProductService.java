package com.Tutienda.service;


import com.Tutienda.entity.FavoriteProduct;

import java.util.List;

public interface IFavoriteProductService {
    void save(FavoriteProduct favoriteProduct);
    void delete(Long id);
    List<FavoriteProduct> findAllByUserId(Long userId);
}
