package com.mnp.store.api.controllers;

import java.util.List;
import java.util.Optional;

import com.mnp.store.contracts.catalogs.ProductService;
import com.mnp.store.domain.catalogs.Product;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class ProductApi {

    private final ProductService productservice;

    public ProductApi(ProductService productService) {
        this.productservice = productService;
    }

    @PostMapping("/product")
    public void addProduct(String isbn, String title, int price, String author, String category) {
        productservice.addProduct(isbn, title, price, author, category);
    }

    @GetMapping("/product")
    public List<Product> listProducts () {
        return productservice.listAllProducts();
    }

    @GetMapping("/product/{isbn}")
    public Optional <Product> productByISBN (String isbn) {
        return productservice.getProductByISBN(isbn);
    }

    @GetMapping("/product/{category}")
    public Optional <Product> productByCategory (String category) {
        return productservice.getProductByGategory(category);
    }

}
