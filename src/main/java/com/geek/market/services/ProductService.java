package com.geek.market.services;

import com.geek.market.entities.Product;
import com.geek.market.repositories.ProductRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
@AllArgsConstructor
public class ProductService {
    private ProductRepository productRepository;

    public Page<Product> findAll(int page, int size, Specification<Product> spec) {
        return productRepository.findAll(spec, PageRequest.of(page, size));
    }

    public Optional<Product> findByID(Long id) {
        return productRepository.findById(id);
    }
    public void save(Product product){
        productRepository.save(product);
    }

    public void deleteById(Long id) {
        productRepository.deleteById(id);
    }

    public void deleteAll() {
        productRepository.deleteAll();
    }

    public Product saveOrUpdate(Product product) {
        return productRepository.save(product);
    }


}
