package com.mnp.store.domain.catalogs;

import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long>{

    List<Product> findItemsByOrderNumberIgnoreCase(long orderNumber);

}