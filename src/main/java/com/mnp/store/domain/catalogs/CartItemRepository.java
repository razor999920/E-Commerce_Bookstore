package com.mnp.store.domain.catalogs;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface CartItemRepository extends JpaRepository<CartItem, Long> {
    Optional<CartItem> findOneByIdIgnoreCase(long id);

    Optional<CartItem> findOneByCart (Cart cart);
}
