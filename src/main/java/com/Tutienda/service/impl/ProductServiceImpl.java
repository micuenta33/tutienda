package com.Tutienda.service.impl;



import com.Tutienda.entity.product.Product;

import com.Tutienda.repository.IProductRepository;
import com.Tutienda.service.IProductService;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;


@Service
public class ProductServiceImpl implements IProductService {
    private final IProductRepository iProductRepository;

    public ProductServiceImpl(IProductRepository iProductRepository) {
        this.iProductRepository = iProductRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public Page<Product> findAll(Pageable pageable) {
        return iProductRepository.findAll(pageable);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Product> findAll() {
        return iProductRepository.findAll();
    }


    @Override
    @Transactional
    public Product save(Product product) {
        product.setCreatedAt(LocalDateTime.now());
        return iProductRepository.save(product);
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        iProductRepository.deleteById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Product> findById(Long id) {
        return iProductRepository.findById(id);
    }



    @Override
    public List<Product> findByBestRatingGreaterThanEqual(Integer rating) {
        return iProductRepository.findByRatingGreaterThanEqual(rating);
    }

}
