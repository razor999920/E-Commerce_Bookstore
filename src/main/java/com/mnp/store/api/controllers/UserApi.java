package com.mnp.store.api.controllers;

import com.mnp.store.common.http.ResponseUtils;
import com.mnp.store.contracts.dtos.BuyingStatistics;
import com.mnp.store.contracts.users.UserService;
import com.mnp.store.contracts.users.dtos.CreateUserRequestDto;
import com.mnp.store.contracts.users.dtos.UserResponseDto;
import com.mnp.store.domain.Purchase;
import com.mnp.store.domain.User;
import com.mnp.store.domain.exceptions.BadRequestException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpStatusCodeException;

import javax.validation.Valid;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

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
            throw new BadRequestException("Invalid id", "User", "idnull");
        }
        User result = userService.save(user);
        return ResponseEntity.ok()
                .body(result);
    }

    @GetMapping("/users")
    public ResponseEntity<List<User>> getAllUsers(Pageable pageable,
                                                  @RequestParam(required = false, defaultValue = "false") boolean withRolesAndReview) {
        Page<User> page;
        if (withRolesAndReview) {
            page = userService.findAllWithRolesAndReviews(pageable);
        } else {
            page = userService.findAll(pageable);
        }
        return ResponseEntity.ok().body(page.getContent());
    }

    @GetMapping("/users/{username}")
    public ResponseEntity<UserResponseDto> getByUsername(@PathVariable String username) {
        return ResponseUtils.ofOptional(userService.getUserByUsername(username));
    }

    @DeleteMapping("/users/{username}")
    public void delete(@PathVariable String username) {
        userService.deleteUser(username);
    }

    @GetMapping("/users/statistics")
    public ResponseEntity<BuyingStatistics> getBuyingStatistics() {
        BuyingStatistics statistics = userService.getBuyingStatistics();
        return ResponseEntity.ok(statistics);
    }
}

