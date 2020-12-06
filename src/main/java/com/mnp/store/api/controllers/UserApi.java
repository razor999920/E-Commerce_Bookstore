package com.mnp.store.api.controllers;

import com.mnp.store.common.http.ResponseUtils;
import com.mnp.store.contracts.dtos.BuyingStatResultDto;
import com.mnp.store.contracts.dtos.BuyingStatistics;
import com.mnp.store.contracts.users.UserService;
import com.mnp.store.contracts.users.dtos.CreateUserRequestDto;
import com.mnp.store.contracts.users.dtos.UserResponseDto;
import com.mnp.store.domain.Address;
import com.mnp.store.domain.Purchase;
import com.mnp.store.domain.User;
import com.mnp.store.domain.constants.RoleConstants;
import com.mnp.store.domain.exceptions.BadRequestException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpStatusCodeException;

import javax.validation.Valid;
import java.net.URISyntaxException;
import java.util.*;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class UserApi {

    private final UserService userService;

    public UserApi(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/users")
    public ResponseEntity<UserResponseDto> create(@Valid @RequestBody CreateUserRequestDto userInfo) {
        return new ResponseEntity<>(userService.createUser(userInfo), HttpStatus.CREATED);
    }

    @PutMapping("/users")
    public ResponseEntity<User> updateUser(@Valid @RequestBody User user) throws URISyntaxException {
        if (user.getId() == null) {
            throw new BadRequestException("Invalid id");
        }
        User result = userService.save(user);
        return ResponseEntity.ok()
                .body(result);
    }

    @GetMapping("/users")
    @PreAuthorize("hasAuthority(\"" + RoleConstants.ADMIN + "\")")
    public ResponseEntity<Page<User>> getAllUsers(Pageable pageable,
                                                  @RequestParam(required = false, defaultValue = "false") boolean withRolesAndReview) {
        Page<User> page;
        if (withRolesAndReview) {
            page = userService.findAllWithRolesAndReviews(pageable);
        } else {
            page = userService.findAll(pageable);
        }
        return ResponseEntity.ok().body(page);
    }

    @GetMapping("/users/{username}")
    public ResponseEntity<UserResponseDto> getByUsername(@PathVariable String username) {
        return ResponseUtils.ofOptional(userService.getUserByUsername(username));
    }

    @DeleteMapping("/users/{username}")
    @PreAuthorize("hasAuthority(\"" + RoleConstants.ADMIN + "\")")
    public void delete(@PathVariable String username) {
        userService.deleteUser(username);
    }

    @GetMapping("/users/statistics")
    @PreAuthorize("hasAuthority(\"" + RoleConstants.ADMIN + "\")")
    public ResponseEntity<List<BuyingStatResultDto>> getBuyingStatistics() {
        List<BuyingStatistics> statistics = userService.getBuyingStatistics();
        return ResponseEntity.ok(statistics.stream().map(s -> new BuyingStatResultDto(UUID.randomUUID().toString(), s.getSpending())).collect(Collectors.toList()));
    }

    @GetMapping("/users/orders")
    @PreAuthorize("hasAuthority(\"" + RoleConstants.USER + "\")")
    public ResponseEntity<List<Purchase>> getUserOrders() {
        User user = userService.getCurrentUser()
                .orElseThrow(() -> new BadRequestException("Could not find user"));

        return ResponseEntity.ok(new ArrayList<>(user.getPurchases()));
    }

    @GetMapping("/users/addresses")
    @PreAuthorize("hasAuthority(\"" + RoleConstants.USER + "\")")
    public ResponseEntity<Set<Address>> getAddresses() {
        User user = userService.getCurrentUser()
                .orElseThrow(() -> new BadRequestException("Invalid user session"));
        return ResponseEntity.ok().body(user.getAddresses());
    }
}

