package com.mnp.store.domain.repository;

import com.mnp.store.domain.Book;
import com.mnp.store.domain.Purchase;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Spring Data  repository for the Purchase entity.
 */
@Repository
public interface PurchaseRepository extends JpaRepository<Purchase, Long> {

    @Query(value = "select distinct purchase from Purchase purchase left join fetch purchase.addresses",
            countQuery = "select count(distinct purchase) from Purchase purchase")
    Page<Purchase> findAllWithEagerRelationships(Pageable pageable);

    @Query("select distinct purchase from Purchase purchase left join fetch purchase.addresses")
    List<Purchase> findAllWithEagerRelationships();

    @Query("select purchase from Purchase purchase left join fetch purchase.addresses where purchase.id =:id")
    Optional<Purchase> findOneWithEagerRelationships(@Param("id") Long id);
}


