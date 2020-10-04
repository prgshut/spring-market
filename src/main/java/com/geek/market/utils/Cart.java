package com.geek.market.utils;


import com.geek.market.entities.Order;
import com.geek.market.entities.OrderItem;
import com.geek.market.entities.Product;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


@Component
@Scope(value = WebApplicationContext.SCOPE_SESSION, proxyMode = ScopedProxyMode.TARGET_CLASS)
@NoArgsConstructor
@Data
public class Cart {
    private List<OrderItem> items;
    private int price;

    @PostConstruct
    public void init() {
        items = new ArrayList<>();
    }

    public void addOrIncrement(Product p) {
        for (OrderItem o : items) {
            if (o.getProduct().getId().equals(p.getId())) {
                o.incrementQuantity();
                recalculate();
                return;
            }
        }
        items.add(new OrderItem(p));
        recalculate();
    }

    public void incrementOnly(Long productId) {
        for (OrderItem o : items) {
            if (o.getProduct().getId().equals(productId)) {
                o.incrementQuantity();
                recalculate();
                return;
            }
        }
    }

    public void decrementOrRemove(Long productId) {
        Iterator<OrderItem> iter = items.iterator();
        while (iter.hasNext()) {
            OrderItem o = iter.next();
            if (o.getProduct().getId().equals(productId)) {
                o.decrementQuantity();
                if (o.getQuantity() == 0) {
                    iter.remove();
                }
                recalculate();
                return;
            }
        }
    }

    public void remove(Long productId) {
        Iterator<OrderItem> iter = items.iterator();
        while (iter.hasNext()) {
            OrderItem o = iter.next();
            if (o.getProduct().getId().equals(productId)) {
                iter.remove();
                recalculate();
                return;
            }
        }
    }
    public void addOrder(Order order){
        for (OrderItem oi:items){
            oi.setOrder(order);
        }
    }

    public void recalculate() {
        price = 0;
        for (OrderItem o : items) {
            price += o.getPrice();
        }
    }
    public void clear(){
        items.clear();
        price=0;
    }
}
