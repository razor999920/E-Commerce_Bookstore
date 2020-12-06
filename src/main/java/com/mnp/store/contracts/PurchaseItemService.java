package com.mnp.store.contracts;

import com.mnp.store.domain.PurchaseItem;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link PurchaseItem}.
 */
public interface PurchaseItemService {

    /**
     * Save a purchaseItem.
     *
     * @param purchaseItem the entity to save.
     * @return the persisted entity.
     */
    PurchaseItem save(PurchaseItem purchaseItem);

    /**
     * Get all the purchaseItems.
     *
     * @return the list of entities.
     */
    List<PurchaseItem> findAll();

    /**
     * Get all the PurchaseItemDTO where Book is {@code null}.
     *
     * @return the {@link List} of entities.
     */
    List<PurchaseItem> findAllWhereBookIsNull();


    /**
     * Get the "id" purchaseItem.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<PurchaseItem> findOne(Long id);

    /**
     * Delete the "id" purchaseItem.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);

    List<PurchaseItem> findByIds(List<Long> ids);
}

