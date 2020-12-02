package com.mnp.store.contracts.catalogs;

import com.mnp.store.domain.catalogs.Product;

import java.util.List;
import java.util.Optional;
public interface ProductService {

    void addProduct (String isbn, String title, int price, String author, String category);

    List<Product> listAllProducts ();

    Optional<Product> getProductByISBN (String isbn);

    Optional<Product> getProductByGategory (String category);
}
