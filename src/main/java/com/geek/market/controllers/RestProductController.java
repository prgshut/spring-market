package com.geek.market.controllers;


import com.geek.market.entities.Product;
import com.geek.market.exceptions.ResourceNotFoundException;
import com.geek.market.services.ProductService;
import com.geek.market.utils.ProductFilter;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/v1/products")
@AllArgsConstructor
public class RestProductController {
    private ProductService productService;


@GetMapping
    public Page<Product> findPaginated( @RequestParam(defaultValue = "1", name = "p") int page,
                                        @RequestParam(defaultValue = "5", required = false) int size,
                                        @RequestParam(required = false) Map<String, String> params) {
        ProductFilter productFilter = new ProductFilter(params);
        Specification<Product> spec = productFilter.getSpec();
        Page<Product> resultPage = productService.findAll(page-1, size,spec);
        if (page > resultPage.getTotalPages()) {
            throw new ResourceNotFoundException("err");
        }

        return resultPage;
    }

    @PostMapping
    public Product createProduct(@RequestBody Product p) {
        p.setId(null);
        return productService.saveOrUpdate(p);
    }

    @PutMapping
    public Product updateProduct(@RequestBody Product p) {
        return productService.saveOrUpdate(p);
    }

    @DeleteMapping
    public void deleteAll() {
        productService.deleteAll();
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable Long id) {
        productService.deleteById(id);
    }
}
