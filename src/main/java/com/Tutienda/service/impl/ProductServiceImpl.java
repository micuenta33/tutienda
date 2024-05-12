package com.Tutienda.service.impl;

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
    public Page<Product> findFilteredAndPaginatedProducts(int page, String category, String gender,String type, int pageSize) {
        Pageable pageRequest = PageRequest.of(page, pageSize);
        List<Product> filteredProducts = productRepository.findAll();
        if (category != null && !category.isEmpty()) {
            filteredProducts = filterProductsByCategory(category, filteredProducts,gender,type);
        }
        if (gender != null && !gender.isEmpty()) {
            filteredProducts = filterProductsByGender(gender, filteredProducts);
        }
        return PageUtils.createPage(filteredProducts, pageRequest);
    }

    private List<Product> filterProductsByCategory(String category, List<Product> productList,String gender,String type) {
        List<Product> filteredProducts = new ArrayList<>();
        // Filtrar la lista de productos según la categoría
        switch (category) {
            case "clothing":
                filteredProducts = productList.stream()
                        .filter(product -> product instanceof Clothing)
                        .collect(Collectors.toList());
                if (type != null && !type.isEmpty()) {
                    filteredProducts = filteredProducts.stream()
                            .filter(product -> ((Clothing) product).getType().getTypeEnum().equalsIgnoreCase(type))
                            .collect(Collectors.toList());
                }
                if (gender != null && !gender.isEmpty()) {
                    filteredProducts = filterProductsByGender(gender, filteredProducts);
                }
                break;
            case "shoes":
                filteredProducts = productList.stream()
                        .filter(product -> product instanceof Shoe)
                        .collect(Collectors.toList());
                if (type != null && !type.isEmpty()) {
                    filteredProducts = filteredProducts.stream()
                            .filter(product -> ((Shoe) product).getType().getTypeEnum().equalsIgnoreCase(type))
                            .collect(Collectors.toList());
                }
                if (gender != null && !gender.isEmpty()) {
                    filteredProducts = filterProductsByGender(gender, filteredProducts);
                }
                break;
            case "watches":
                filteredProducts = productList.stream()
                        .filter(product -> product instanceof Watch)
                        .collect(Collectors.toList());
                if (type != null) {
                    filteredProducts = filteredProducts.stream()
                            .filter(product -> ((Watch) product).getWatchType().getType().equalsIgnoreCase(type))
                            .collect(Collectors.toList());
                }
                if (gender != null && !gender.isEmpty()) {
                    filteredProducts = filterProductsByGender(gender, filteredProducts);
                }
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
                if (gender != null && !gender.isEmpty()) {
                    filteredProducts = filterProductsByGender(gender, filteredProducts);
                }
                break;
            default:
                // Si la categoría no coincide, mantener la lista sin cambios
                filteredProducts = productList;
                break;
        }
        return filteredProducts;
    }
    private List<Product> filterProductsByGender(String gender, List<Product> productList) {
        return  productList.stream()
                .filter(product -> product.getGender().getGenderEnum().equalsIgnoreCase(gender))
                .toList();
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

    @Override
    public List<Product> findByName(String term) {
        return productRepository.findByNameContainingIgnoreCase(term);
    }

}
