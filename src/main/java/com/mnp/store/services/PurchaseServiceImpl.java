package com.mnp.store.services;

import com.mnp.store.contracts.PurchaseService;
import com.mnp.store.domain.Purchase;
import com.mnp.store.domain.repository.PurchaseRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
public class PurchaseServiceImpl implements PurchaseService {


    private final PurchaseRepository purchaseRepository;

    public PurchaseServiceImpl(PurchaseRepository purchaseRepository) {
        this.purchaseRepository = purchaseRepository;
    }

    @Override
    public Purchase save(Purchase purchase) {
        return purchaseRepository.save(purchase);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<Purchase> findAll(Pageable pageable) {
        return purchaseRepository.findAll(pageable);
    }


    public Page<Purchase> findAllWithEagerRelationships(Pageable pageable) {
        return purchaseRepository.findAll(pageable);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Purchase> findOne(Long id) {
        return purchaseRepository.findById(id);
    }

    @Override
    public void delete(Long id) {
        purchaseRepository.deleteById(id);
    }
}


