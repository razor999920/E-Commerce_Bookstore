package com.mnp.store.services.catalogs;

import java.util.Optional;

import com.mnp.store.contracts.catalogs.CartService;
import com.mnp.store.domain.catalogs.Cart;
import com.mnp.store.domain.catalogs.CartItem;
import com.mnp.store.domain.catalogs.CartRepository;

import com.mnp.store.domain.catalogs.Product;
import com.mnp.store.domain.users.User;

import org.springframework.stereotype.Service;

@Service
public class CartServiceImpl implements CartService{
    private final CartRepository cartRepository;

    public CartServiceImpl (CartRepository cartRepository) {
        this.cartRepository = cartRepository;
    }

    public void addCartItem (CartItem cartitem, User user, long id) {
        Cart newCart = new Cart();
        newCart.setItems(cartitem);
        newCart.setId(id);
        newCart.setUser(user);
        cartRepository.save(newCart);
    }

    public void removeCartItem (CartItem cartitem) {
        Cart newCart = new Cart ();

        newCart.getItems().remove(cartitem);

        cartRepository.save(newCart);
    }


}
