package com.mnp.store.contracts.catalogs;

import com.mnp.store.domain.catalogs.product.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface ProductService {
    Page<Product> getAllProducts();

    Page<Product> getProductsByCategory(Pageable pageable, String category);

    Page<Product> getProductsByFilter(String key, String value);

    Product addProduct(Product product);

    Optional<Product> getProductById(long id);

    void removeProduct(long id);
}
