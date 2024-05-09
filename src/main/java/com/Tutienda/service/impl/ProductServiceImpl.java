package com.Tutienda.service.impl;



import com.Tutienda.entity.enums.Gender;
import com.Tutienda.entity.product.*;

import com.Tutienda.repository.IProductRepository;
import com.Tutienda.service.IProductService;

import com.Tutienda.util.paginator.PageUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
public class ProductServiceImpl implements IProductService {
    private final IProductRepository productRepository;

    public ProductServiceImpl(IProductRepository iProductRepository) {
        this.productRepository = iProductRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public Page<Product> findAll(Pageable pageable) {
        return productRepository.findAll(pageable);
    }


    @Override
    @Transactional(readOnly = true)
    public List<Product> findAll() {
        return productRepository.findAll();
    }

    @Override
    public Page<Product> findFilteredAndPaginatedProducts(int page, String categoria, String gender, int pageSize) {
        Pageable pageRequest = PageRequest.of(page, pageSize);
        List<Product> filteredProducts = productRepository.findAll();
        if (categoria != null) {
            filteredProducts = filterProductsByCategory(categoria, filteredProducts);
        }
        if (gender != null) {
            filteredProducts = filterProductsByGender(gender, filteredProducts);
        }
        return PageUtils.createPage(filteredProducts, pageRequest);
    }

    private List<Product> filterProductsByCategory(String categoria, List<Product> productList) {
        List<Product> filteredProducts = new ArrayList<>();

        // Filtrar la lista de productos según la categoría
        switch (categoria) {
            case "clothing":
                filteredProducts = productList.stream()
                        .filter(product -> product instanceof Clothing)
                        .collect(Collectors.toList());
                break;
            case "shoes":
                filteredProducts = productList.stream()
                        .filter(product -> product instanceof Shoe)
                        .collect(Collectors.toList());
                break;
            case "watches":
                filteredProducts = productList.stream()
                        .filter(product -> product instanceof Watch)
                        .collect(Collectors.toList());
                break;
            case "glasses":
                filteredProducts = productList.stream()
                        .filter(product -> product instanceof Glass)
                        .collect(Collectors.toList());
                break;
            case "jewelry":
                filteredProducts = productList.stream()
                        .filter(product -> product instanceof Jewelry)
                        .collect(Collectors.toList());
                break;
            case "bags":
                filteredProducts = productList.stream()
                        .filter(product -> product instanceof Bag)
                        .collect(Collectors.toList());
                break;
            default:
                // Si la categoría no coincide, mantener la lista sin cambios
                filteredProducts = productList;
                break;
        }
        return filteredProducts;
    }

    private List<Product> filterProductsByGender(String gender, List<Product> productList) {
        List<Product> filteredProducts = new ArrayList<>();
        switch (gender) {
            case "women":
                filteredProducts = productList.stream()
                        .filter(product -> product.getGender() == Gender.MUJER)
                        .collect(Collectors.toList());
                break;
            case "men":
                filteredProducts = productList.stream()
                        .filter(product -> product.getGender() == Gender.HOMBRE)
                        .collect(Collectors.toList());
                break;
            case "unisex":
                filteredProducts = productList.stream()
                        .filter(product -> product.getGender() == Gender.UNISEX)
                        .collect(Collectors.toList());
                break;
        }
        return filteredProducts;
    }


    @Override
    @Transactional
    public Product save(Product product) {
        product.setCreatedAt(LocalDateTime.now());
        return productRepository.save(product);
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        productRepository.deleteById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Product> findById(Long id) {
        return productRepository.findById(id);
    }



    @Override
    public List<Product> findByBestRatingGreaterThanEqual(Integer rating) {
        return productRepository.findByRatingGreaterThanEqual(rating);
    }

}
