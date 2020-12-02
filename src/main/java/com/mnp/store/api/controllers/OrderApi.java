package com.mnp.store.api.controllers;

import java.util.List;

import com.mnp.store.contracts.catalogs.OrderService;
import com.mnp.store.domain.catalogs.Order;
import com.mnp.store.domain.catalogs.Product;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class OrderApi {
    private final OrderService orderService;

    public OrderApi(OrderService orderService) {
        this.orderService = orderService;
    }

    public void addProduct(long order_number, List<Product> products, int total) {
        orderService.add_to_order(order_number, products, total);
    }
    
    public List<Order> listAllItems () {
        return orderService.listAllItems();
    }

    public List<Product> listAllProducts (long orderNumber) { 
        return orderService.listAllProducts(orderNumber);
    }
}