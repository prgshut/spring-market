package com.geek.market.controllers;

import com.geek.market.entities.Order;
import com.geek.market.entities.User;
import com.geek.market.services.OrderService;
import com.geek.market.services.UserService;
import com.geek.market.utils.Cart;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.security.Principal;

@Controller
@RequestMapping("/orders")
@AllArgsConstructor
public class OrderController {
    private OrderService orderService;
    private UserService userService;
    private Cart cart;

    @GetMapping
    public String firstRequest(Model model, Principal principal) {
        User user = userService.findByUsername(principal.getName());
        model.addAttribute("username", principal.getName());
        model.addAttribute("orders", orderService.findByUser(user));
        return "orders";
    }

    @GetMapping("/create_order")
    public String createOrder(Model model, Principal principal) {
        User user = userService.findByUsername(principal.getName());
        model.addAttribute("cart", cart);
        model.addAttribute("user", user);
        return "create_order";
    }

    @PostMapping("/save_order")
    public String saveOrder(Principal principal,
                            @RequestParam(name = "name") String name,
                            @RequestParam(name = "phone") String phone,
                            @RequestParam(name = "address") String address) {
        User user = userService.findByUsername(principal.getName());
        Order order = new Order(user,cart,address,phone);
        orderService.save(order);
        cart.clear();
        return "redirect:/orders";
    }
}
