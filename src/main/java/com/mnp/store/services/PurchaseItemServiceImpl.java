package com.mnp.store.services;

import com.mnp.store.contracts.PurchaseItemService;
import com.mnp.store.domain.PurchaseItem;
import com.mnp.store.domain.repository.PurchaseItemRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
@Transactional
public class PurchaseItemServiceImpl implements PurchaseItemService {

    private final PurchaseItemRepository purchaseItemRepository;

    public PurchaseItemServiceImpl(PurchaseItemRepository purchaseItemRepository) {
        this.purchaseItemRepository = purchaseItemRepository;
    }

    @Override
    public PurchaseItem save(PurchaseItem purchaseItem) {
        return purchaseItemRepository.save(purchaseItem);
    }

    @Override
    @Transactional(readOnly = true)
    public List<PurchaseItem> findAll() {
        return purchaseItemRepository.findAll();
    }


    /**
     * Get all the purchaseItems where Book is {@code null}.
     *
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public List<PurchaseItem> findAllWhereBookIsNull() {
        return StreamSupport
                .stream(purchaseItemRepository.findAll().spliterator(), false)
                .filter(purchaseItem -> purchaseItem.getBook() == null)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<PurchaseItem> findOne(Long id) {
        return purchaseItemRepository.findById(id);
    }

    @Override
    public void delete(Long id) {
        purchaseItemRepository.deleteById(id);
    }

    @Override
    public List<PurchaseItem> findByIds(List<Long> ids) {
        return purchaseItemRepository.findAllById(ids);
    }
}


