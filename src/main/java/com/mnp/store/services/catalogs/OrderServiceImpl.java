package com.mnp.store.services.catalogs;

import com.mnp.store.contracts.catalogs.OrderService;
import com.mnp.store.domain.catalogs.Order;
import com.mnp.store.domain.catalogs.OrderRepository;
import com.mnp.store.domain.catalogs.Product;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {
    private final OrderRepository orderRepository;

    public OrderServiceImpl (OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @Override
    public void add_to_order(long orderNumber, List<Product> products, int total) {
        Order order = new Order();

        order.set_order_number(orderNumber);
        order.set_items(products);
        order.set_total(calculate_total (products));
    }

    public int calculate_total (List<Product> products) {
        int total = 0;

        for (int i = 0; i < products.size(); i++) {
            total = total + products.get(i).getPrice();
        }
        return total;
    }

    @Override
    public List<Order> listAllItems() {
        return orderRepository.findAll();
    }

    @Override
    public List<Product> listAllProducts (long orderNumber) {
        return orderRepository.findItemsByOrderNumberIgnoreCase(orderNumber);
    }

}