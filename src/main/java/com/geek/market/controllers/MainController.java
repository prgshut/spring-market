package com.geek.market.controllers;

import com.geek.market.services.ProductService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@AllArgsConstructor
@RequestMapping("/products")
public class MainController {
    private ProductService productService;

    @GetMapping
    public String productAll(Model model,
                             @RequestParam(defaultValue = "1", name = "p") Integer page,
                             @RequestParam(required = false, defaultValue = "0") int min,
                             @RequestParam(required = false,defaultValue = "0") int max
    ) {
        if (page < 1) {
            page = 1;
        }
        if (min != 0 && max != 0) {
            model.addAttribute("products",
                    productService.findByPriceGreaterThanEqualAndPriceLessThan(page-1,5,max, min));
            return "products";
        }
        if (min > 0 && max == 0) {
            model.addAttribute("products", productService.findByPriceGreaterLessThan(page-1,5,min));
            return "products";
        }
        if (min == 0 && max > 0) {
            model.addAttribute("products", productService.findByPriceGreaterThanEqual(page-1,5,max));
            return "products";
        }

            model.addAttribute("products", productService.findAll(page - 1, 5));
            return "products";

    }


}
