package com.geek.market.controllers;

import com.geek.market.entities.Product;
import com.geek.market.services.ProductService;
import com.geek.market.utils.ProductFilter;
import lombok.AllArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Controller
@AllArgsConstructor
public class MainController {
    private ProductService productService;

    @GetMapping("/products")
    public String productAll(Model model,
                             @RequestParam(defaultValue = "1", name = "p") Integer page,
                             @RequestParam(required = false) Map<String, String> params
    ) {
        if (page < 1) {
            page = 1;
        }
        ProductFilter productFilter = new ProductFilter(params);
        Specification<Product> spec = productFilter.getSpec();
        model.addAttribute("products", productService.findAll(page - 1, 5, spec));
        model.addAttribute("filterDefinition", productFilter.getFilterDefinition());
        return "products";

    }

    @GetMapping("/update_product/{id}")
    public String updateProduct(Model model, @PathVariable Integer id) {
        model.addAttribute("product", productService.findID(id).get());
        return "update_product";
    }

    @PostMapping("/update_product")
    public String productAdd(@RequestParam Integer id, @RequestParam String title, @RequestParam int price) {
        productService.save(id, title, price);
        return "redirect:/products";
    }

}
