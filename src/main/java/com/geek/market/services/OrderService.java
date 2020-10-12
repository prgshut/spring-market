package com.geek.market.services;


import com.geek.market.entities.Order;
import com.geek.market.entities.User;
import com.geek.market.repositories.OrderRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class OrderService {
    private OrderRepository orderRepository;

    public List<Order> findByUser(User user) {
        return orderRepository.findOrdersByUserEquals(user);
    }

    public void save(Order order){
        orderRepository.save(order);
    }
}
