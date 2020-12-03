package com.mnp.store.services.catalogs;

import com.mnp.store.contracts.catalogs.ProductService;
import com.mnp.store.domain.catalogs.product.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
public class ProductServiceImpl implements ProductService {
    @Override
    public Page<Product> getAllProducts() {
        return null;
    }

    @Override
    public Page<Product> getProductsByCategory(Pageable pageable, String category) {
        return null;
    }

    @Override
    public Page<Product> getProductsByFilter(String key, String value) {
        return null;
    }

    @Override
    public Product addProduct(Product product) {
        return null;
    }

    @Override
    public Optional<Product> getProductById(long id) {
        return Optional.empty();
    }

    @Override
    public void removeProduct(long id) {

    }
}
