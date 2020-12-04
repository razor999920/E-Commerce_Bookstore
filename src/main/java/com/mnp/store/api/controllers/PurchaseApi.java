package com.mnp.store.api.controllers;

import com.mnp.store.contracts.PurchaseService;
import com.mnp.store.contracts.dtos.BuyingStatistics;
import com.mnp.store.domain.Purchase;
import com.mnp.store.domain.exceptions.BadRequestException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpStatusCodeException;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class PurchaseApi {

    private static final String ENTITY_NAME = "purchase";

    private final PurchaseService purchaseService;

    public PurchaseApi(PurchaseService purchaseService) {
        this.purchaseService = purchaseService;
    }

    @PostMapping("/purchases")
    public ResponseEntity<Purchase> createPurchase(@Valid @RequestBody Purchase purchase) throws URISyntaxException {
        if (purchase.getId() != null) {
            throw new BadRequestException("A new purchase cannot already have an ID", ENTITY_NAME, "idexists");
        }
        Purchase result = purchaseService.save(purchase);
        return ResponseEntity.created(new URI("/api/purchases/" + result.getId()))
                .body(result);
    }

    @PutMapping("/purchases")
    public ResponseEntity<Purchase> updatePurchase(@Valid @RequestBody Purchase purchase) throws URISyntaxException {
        if (purchase.getId() == null) {
            throw new BadRequestException("Invalid id", ENTITY_NAME, "idnull");
        }
        Purchase result = purchaseService.save(purchase);
        return ResponseEntity.ok()
                .body(result);
    }

    @GetMapping("/purchases")
    public ResponseEntity<List<Purchase>> getAllPurchases(Pageable pageable, @RequestParam(required = false, defaultValue = "false") boolean eagerload) {
        Page<Purchase> page;
        if (eagerload) {
            page = purchaseService.findAllWithEagerRelationships(pageable);
        } else {
            page = purchaseService.findAll(pageable);
        }
        return ResponseEntity.ok().body(page.getContent());
    }

    @GetMapping("/purchases/{id}")
    public ResponseEntity<Purchase> getPurchase(@PathVariable Long id) {
        Optional<Purchase> purchase = purchaseService.findOne(id);
        return purchase.map(response -> ResponseEntity.ok().body(response))
                .orElseThrow(() -> new HttpStatusCodeException(HttpStatus.NOT_FOUND) {
                });
    }

    @DeleteMapping("/purchases/{id}")
    public ResponseEntity<Void> deletePurchase(@PathVariable Long id) {
        purchaseService.delete(id);
        return ResponseEntity.noContent().build();
    }
}


