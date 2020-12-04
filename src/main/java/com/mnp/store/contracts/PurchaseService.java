package com.mnp.store.contracts;

import com.mnp.store.domain.Purchase;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface PurchaseService {

    /**
     * Save a purchase.
     *
     * @param purchase the entity to save.
     * @return the persisted entity.
     */
    Purchase save(Purchase purchase);

    /**
     * Get all the purchases.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<Purchase> findAll(Pageable pageable);

    /**
     * Get all the purchases with eager load of many-to-many relationships.
     *
     * @return the list of entities.
     */
    Page<Purchase> findAllWithEagerRelationships(Pageable pageable);


    /**
     * Get the "id" purchase.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<Purchase> findOne(Long id);

    /**
     * Delete the "id" purchase.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}


