package com.mnp.store.contracts.catalogs;

import java.util.List;

import com.mnp.store.domain.catalogs.Order;
import com.mnp.store.domain.catalogs.Product;
public interface OrderService {

    void add_to_order (long orderNumber, List<Product> products, int total);

    List<Order> listAllItems ();

    List<Product> listAllProducts (long orderNumber);
}