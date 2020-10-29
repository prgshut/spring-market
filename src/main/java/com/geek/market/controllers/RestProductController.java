package com.geek.market.controllers;


import com.geek.market.entities.Product;
import com.geek.market.entities.ProductType;
import com.geek.market.exceptions.ResourceNotFoundException;
import com.geek.market.services.ProductService;
import com.geek.market.services.ProductTypeService;
import com.geek.market.utils.ProductFilter;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/products")
@AllArgsConstructor
public class RestProductController {
    private ProductService productService;
    private ProductTypeService productTypeService;

    @GetMapping(produces = "application/json") // /api/v1/products
    public Object[] getAllProducts(@RequestParam(defaultValue = "1", name = "p") Integer page,
                                        @RequestParam Map<String, String> params) {
        if (page < 1) {
            page = 1;
        }
        Object[] out = new Object[2];
        List<ProductType> productType = productTypeService.findAll();
        ProductFilter productFilter = new ProductFilter(params);
        Page<Product> content = productService.findAll(page - 1, 5,productFilter.getSpec());
        out[0]=productType;
        out[1]=content;
        return out;
    }

    @GetMapping(value = "/{id}", produces = "application/json")
    public Product getProductById(@PathVariable Long id) {
        return productService.findByID(id).orElseThrow(() -> new ResourceNotFoundException("Unable to find product with id: " + id));
    }

    @PostMapping(consumes = "application/json", produces = "application/json")
    public Product createProduct(@RequestBody Product p) {
        p.setId(null);
        return productService.saveOrUpdate(p);
    }

    @PutMapping(consumes = "application/json", produces = "application/json")
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
