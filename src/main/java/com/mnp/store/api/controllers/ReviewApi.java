package com.mnp.store.api.controllers;

import com.mnp.store.contracts.BookService;
import com.mnp.store.contracts.ReviewService;
import com.mnp.store.contracts.dtos.CreateReviewDto;
import com.mnp.store.contracts.users.UserService;
import com.mnp.store.domain.Book;
import com.mnp.store.domain.Review;
import com.mnp.store.domain.User;
import com.mnp.store.domain.constants.RoleConstants;
import com.mnp.store.domain.exceptions.BadRequestException;
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
public class ReviewApi {

    private final ReviewService reviewService;
    private final UserService userService;
    private final BookService bookService;

    public ReviewApi(ReviewService reviewService, UserService userService, BookService bookService) {
        this.reviewService = reviewService;
        this.userService = userService;
        this.bookService = bookService;
    }

    @PostMapping("/reviews")
    @PreAuthorize("hasAuthority(\"" + RoleConstants.USER + "\")")
    public ResponseEntity<Review> createReview(@Valid @RequestBody CreateReviewDto request) throws URISyntaxException {
        User user = userService.getCurrentUser()
                .orElseThrow(() -> new BadRequestException("Invalid user session"));

        Book book = bookService.findOne(request.getBookId()).orElseThrow(() -> new BadRequestException("Book doesn't exist"));

        Review newReview = new Review();
        newReview.setRating(request.getRating());
        newReview.setComment(request.getComment());
        newReview.setBook(book);

        newReview.addUser(user);

        Review result = reviewService.save(newReview);

        return ResponseEntity.created(new URI("/api/reviews/" + result.getId()))
                .body(result);
    }

    @PutMapping("/reviews")
    @PreAuthorize("hasAuthority(\"" + RoleConstants.USER + "\")")
    public ResponseEntity<Review> updateReview(@Valid @RequestBody Review review) {
        if (review.getId() == null) {
            throw new BadRequestException("Invalid id");
        }
        Review result = reviewService.save(review);
        return ResponseEntity.ok()
                .body(result);
    }

    @GetMapping("/reviews")
    @PreAuthorize("hasAuthority(\"" + RoleConstants.USER + "\")")
    public List<Review> getAllReviews() {
        return reviewService.findAll();
    }

    @GetMapping("/reviews/{id}")
    @PreAuthorize("hasAuthority(\"" + RoleConstants.USER + "\")")
    public ResponseEntity<Review> getReview(@PathVariable Long id) {
        Optional<Review> review = reviewService.findOne(id);
        return review.map(response -> ResponseEntity.ok().body(response))
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @DeleteMapping("/reviews/{id}")
    @PreAuthorize("hasAuthority(\"" + RoleConstants.ADMIN + "\")")
    public ResponseEntity<Void> deleteReview(@PathVariable Long id) {
        reviewService.delete(id);
        return ResponseEntity.noContent().build();
    }
}


