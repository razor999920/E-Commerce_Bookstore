package com.mnp.store.services.catalogs;

import com.mnp.store.contracts.catalogs.CartItemService;
import com.mnp.store.domain.catalogs.CartItem;
import com.mnp.store.domain.catalogs.CartItemRepository;
import com.mnp.store.domain.catalogs.Product;

import org.springframework.stereotype.Service;

@Service
public class CartItemServiceImpl implements CartItemService {
    private final CartItemRepository cartItemRepository;

    public CartItemServiceImpl (CartItemRepository cartItemRepository) {
        this.cartItemRepository = cartItemRepository;
    }
    
    public void addProductToCart (Product product) {
        CartItem newCartItem = new CartItem();
        
        newCartItem.setProduct(product);

        cartItemRepository.save(newCartItem);
    }

    public void removeProdcutFromCart (Product product) {
        
    }

}
