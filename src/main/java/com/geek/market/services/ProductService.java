package com.geek.market.services;

import com.geek.market.entities.Product;
import com.geek.market.repositories.ProductRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ProductService {
    private ProductRepository productRepository;

    public Page<Product> findAll(int page, int size) {
        return productRepository.findAll(PageRequest.of(page, size));
    }

    public Page<Product> findByPriceGreaterThanEqualAndPriceLessThan(int page, int size, int min, int max) {
        return productRepository.findByPriceGreaterThanEqualAndPriceLessThanEqual(PageRequest.of(page, size), max, min);
    }

    public Page<Product> findByPriceGreaterThanEqual(int page, int size, int max) {
        return productRepository.findByPriceGreaterThanEqual(PageRequest.of(page, size),max);
    }

    public Page<Product> findByPriceGreaterLessThan(int page, int size, int min) {
        return productRepository.findByPriceLessThanEqual(PageRequest.of(page, size),min);
    }

}
