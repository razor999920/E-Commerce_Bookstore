package com.mnp.store.domain.catalogs;

import java.util.Optional;

import com.mnp.store.domain.users.User;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CartRepository extends JpaRepository<Cart, Long>{ 
    Optional<Cart> findOneByIdIgnoreCase(long id);

    Optional<Cart> findOneByUser (User user);


}
