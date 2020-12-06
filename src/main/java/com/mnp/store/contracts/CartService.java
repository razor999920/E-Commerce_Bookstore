package com.mnp.store.contracts;

import com.mnp.store.domain.Cart;

import java.util.List;
import java.util.Optional;

public interface CartService {

    Cart save(Cart cart);

    List<Cart> findAll();

    Optional<Cart> findOne(Long id);

    void delete(Long id);
}


