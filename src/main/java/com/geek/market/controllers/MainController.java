package com.geek.market.controllers;

import com.geek.market.entities.Product;
import com.geek.market.services.ProductService;
import com.geek.market.utils.ProductFilter;
import lombok.AllArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

@Controller
@AllArgsConstructor
@RequestMapping("/products")
public class MainController {
    private ProductService productService;

    @GetMapping
    public String productAll(Model model,
                             @RequestParam(defaultValue = "1", name = "p") Integer page,
                             @RequestParam(required = false)Map<String,String> param
                             ) {
        if (page < 1) {
            page = 1;
        }
        ProductFilter productFilter = new ProductFilter(param);
        Specification<Product> spec = productFilter.
            model.addAttribute("products", productService.findAll(page - 1, 5, spec));
            return "products";

    }


}
