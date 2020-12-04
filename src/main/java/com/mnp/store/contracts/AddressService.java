package com.mnp.store.contracts;

import com.mnp.store.domain.Address;

import java.util.List;
import java.util.Optional;

public interface AddressService {

    /**
     * Save a address.
     *
     * @param address the entity to save.
     * @return the persisted entity.
     */
    Address save(Address address);

    /**
     * Get all the addresses.
     *
     * @return the list of entities.
     */
    List<Address> findAll();


    /**
     * Get the "id" address.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<Address> findOne(Long id);

    /**
     * Delete the "id" address.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}


