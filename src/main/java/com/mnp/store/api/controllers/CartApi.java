package com.mnp.store.api.controllers;

import com.mnp.store.contracts.CartService;
import com.mnp.store.contracts.dtos.CreateCartDto;
import com.mnp.store.contracts.users.UserService;
import com.mnp.store.domain.Cart;
import com.mnp.store.domain.User;
import com.mnp.store.domain.constants.RoleConstants;
import com.mnp.store.domain.exceptions.BadRequestException;
import liquibase.pro.packaged.C;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class CartApi {

    private final CartService cartService;
    private final UserService userService;

    public CartApi(CartService cartService, UserService userService) {
        this.cartService = cartService;
        this.userService = userService;
    }

    @PutMapping("/carts")
    @PreAuthorize("hasAuthority(\"" + RoleConstants.USER + "\")")
    public ResponseEntity<Cart> updateCart(@Valid @RequestBody CreateCartDto cart) {
        User user = userService.getCurrentUser().orElseThrow(() -> new BadRequestException("Invalid User Session"));

        if (user.getCart() == null)
            user.cart(new Cart().blob(cart.getBlob()).user(user));
        else
            user.getCart().blob(cart.getBlob());

        Cart result = cartService.save(user.getCart());
        return ResponseEntity.ok().body(result);
    }

    @GetMapping("/carts")
    @PreAuthorize("hasAuthority(\"" + RoleConstants.ADMIN + "\")")
    public List<Cart> getAllCarts() {
        return cartService.findAll();
    }

    @GetMapping("/carts/current")
    @PreAuthorize("hasAuthority(\"" + RoleConstants.USER + "\")")
    public ResponseEntity<Cart> getCart() {
        User user = userService.getCurrentUser().orElseThrow(() -> new BadRequestException("Invalid user session"));
        Cart cart = user.getCart();
        return ResponseEntity.ok().body(cart);
    }

    @DeleteMapping("/carts")
    @PreAuthorize("hasAuthority(\"" + RoleConstants.USER + "\")")
    public ResponseEntity<Void> deleteCart() {
        User user = userService.getCurrentUser().orElseThrow(() -> new BadRequestException("Invalid user session"));
        user.getCart().setUser(null);
        user.setCart(null);
        userService.save(user);

        return ResponseEntity.noContent().build();
    }
}