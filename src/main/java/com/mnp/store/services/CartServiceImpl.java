package com.mnp.store.services;

import com.mnp.store.contracts.CartService;
import com.mnp.store.domain.Cart;
import com.mnp.store.domain.repository.CartRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class CartServiceImpl implements CartService {

    private final CartRepository cartRepository;

    public CartServiceImpl(CartRepository cartRepository) {
        this.cartRepository = cartRepository;
    }

    @Override
    public Cart save(Cart cart) {
        return cartRepository.save(cart);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Cart> findAll() {
        return cartRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Cart> findOne(Long id) {
        return cartRepository.findById(id);
    }

    @Override
    public void delete(Long id) {
        cartRepository.deleteById(id);
    }
}


