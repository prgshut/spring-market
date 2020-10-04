package com.geek.market.controllers;

import com.geek.market.entities.Customer;
import com.geek.market.entities.Order;
import com.geek.market.entities.OrderItem;
import com.geek.market.services.CustomerService;
import com.geek.market.services.OrderService;
import com.geek.market.utils.Cart;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/orders")
@AllArgsConstructor
public class OrderController {
    private OrderService orderService;
    private CustomerService customerService;
    private Cart cart;

    @GetMapping
    public String firstRequest(Model model) {
        model.addAttribute("orders", orderService.findAll());
        return "orders";
    }
    @PostMapping("/add")
    public String addOrder(@RequestParam String customerName, String phoneNumber, String address){
        Order order = new Order();
        Customer costomer = customerService.findByName(customerName);
        order.setAddress(address);
        order.setPhoneNumber(phoneNumber);
        order.setCustomer(costomer);
        order.setPrice(cart.getPrice());
        cart.addOrder(order);
        order.setItems(cart.getItems());
        orderService.save(order);
        cart.clear();
        return "redirect:/products";
    }
}
