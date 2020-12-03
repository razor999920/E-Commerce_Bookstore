package com.mnp.store.services.catalogs;

import com.mnp.store.contracts.catalogs.ProductService;
import com.mnp.store.domain.catalogs.Product;
import com.mnp.store.domain.catalogs.ProductRepository;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;



@Service
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;

    public ProductServiceImpl (ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

   
    public void addProduct (String isbn, String title, int price, String author, String category) {
        Product newProduct = new Product();

        newProduct.setISBN(isbn);
        newProduct.setTitle(title);
        newProduct.setPrice(price);
        newProduct.setAuthor(author);
        newProduct.setCategory(category);

        productRepository.save(newProduct);
    }

    public List<Product> listAllProducts () {
        return productRepository.findAll();
    }

    public Optional<Product> getProductByISBN (String isbn) {
        return productRepository.findOneByIsbnIgnoreCase(isbn);
    }

    public Optional<Product> getProductByGategory (String category) {
        return productRepository.findOneByCategoryIgnoreCase(category);
    }

    

}
