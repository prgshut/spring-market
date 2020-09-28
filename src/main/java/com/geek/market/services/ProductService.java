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

    public Product findByID(Integer id){
      return   productRepository.getOne(id);

    }
    public Optional<Product> findID(Integer id){
        return productRepository.findById(id);
    }
    public void save(Integer id, String titel, int price){
        Product product = new Product(id,titel,price);
        productRepository.save(product);
    }


}
