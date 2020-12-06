package com.mnp.store.api.controllers;

import com.mnp.store.contracts.BookService;
import com.mnp.store.contracts.PurchaseItemService;
import com.mnp.store.contracts.PurchaseService;
import com.mnp.store.contracts.dtos.CreatePurchaseDto;
import com.mnp.store.contracts.dtos.CreatePurchaseItemDto;
import com.mnp.store.contracts.users.UserService;
import com.mnp.store.domain.*;
import com.mnp.store.domain.exceptions.BadRequestException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpStatusCodeException;

import javax.validation.Valid;
import java.math.BigDecimal;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.*;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class PurchaseApi {

    private final PurchaseService purchaseService;
    private final UserService userService;
    private final PurchaseItemService purchaseItemService;
    private final BookService bookService;

    public PurchaseApi(PurchaseService purchaseService, UserService userService, PurchaseItemService purchaseItemService, BookService bookService) {
        this.purchaseService = purchaseService;
        this.userService = userService;
        this.purchaseItemService = purchaseItemService;
        this.bookService = bookService;
    }

    @PostMapping("/purchases")
    public ResponseEntity<Purchase> createPurchase(@Valid @RequestBody CreatePurchaseDto request) throws URISyntaxException {
        User user = userService.getCurrentUser().orElseThrow(() -> new BadRequestException("Invalid user session"));

        // force current user id on address.
        Address address = request.getAddress();
        address.setUser(user);

        // get price and calculate total
        List<CreatePurchaseItemDto> items = request.getItems();
        List<Long> bookIds = items.stream().map(CreatePurchaseItemDto::getId).collect(Collectors.toList());
        List<Book> books = bookService.getByIds(bookIds);
        Set<PurchaseItem> purchaseItems = new HashSet<>();
        BigDecimal total = BigDecimal.ZERO;
        for (int i = 0; i < request.getItems().size(); i += 1) {
            PurchaseItem item = new PurchaseItem();
            Book book = books.get(i);
            item.setBook(book);
            item.setPrice(book.getPrice());

            CreatePurchaseItemDto itemDesc = items.get(i);
            item.setQuantity(itemDesc.getQuantity());
            purchaseItems.add(item);
            total = total.add(book.getPrice().multiply(BigDecimal.valueOf(itemDesc.getQuantity())));
        }

        Purchase purchase = new Purchase();
        purchase.setFirstname(request.getFirstname());
        purchase.setLastname(request.getLastname());
        purchase.setStatus(request.getStatus());
        purchase.setUser(user);
        purchase.setPurchaseItems(purchaseItems);
        purchase.setAddresses(address);
        purchase.setTotal(total);

        Purchase result = purchaseService.save(purchase);

        return ResponseEntity.created(new URI("/api/purchases/" + result.getId()))
                .body(result);
    }

    @PutMapping("/purchases")
    public ResponseEntity<Purchase> updatePurchase(@Valid @RequestBody Purchase purchase) throws URISyntaxException {
        if (purchase.getId() == null) {
            throw new BadRequestException("Invalid id");
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


