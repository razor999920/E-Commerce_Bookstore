package com.mnp.store.api.controllers;

import com.mnp.store.contracts.ReviewService;
import com.mnp.store.contracts.users.UserService;
import com.mnp.store.domain.Book;
import com.mnp.store.domain.Review;
import com.mnp.store.domain.User;
import com.mnp.store.domain.exceptions.BadRequestException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class ReviewApi {

    private static final String ENTITY_NAME = "review";
    private final ReviewService reviewService;
    private final UserService userService;

    public ReviewApi(ReviewService reviewService, UserService userService) {
        this.reviewService = reviewService;
        this.userService = userService;
    }

    @PostMapping("/reviews")
    public ResponseEntity<Review> createReview(
            @Valid @RequestBody @Min(1) @Max(5) int rating,
            @Valid @RequestBody @Size(max = 254) String comment,
            @Valid @RequestBody long bookId) throws URISyntaxException {
        User user = userService.getCurrentUser()
                .orElseThrow(() -> new BadRequestException("Invalid user session", ENTITY_NAME, "idnul"));

        Book book = new Book();
        book.setId(bookId);

        Review newReview = new Review();
        newReview.setRating(rating);
        newReview.setComment(comment);
        newReview.setBook(book);

        newReview.getUsers().add(user);

        Review result = reviewService.save(newReview);

        return ResponseEntity.created(new URI("/api/reviews/" + result.getId()))
                .body(result);
    }

    @PutMapping("/reviews")
    public ResponseEntity<Review> updateReview(@Valid @RequestBody Review review) {
        if (review.getId() == null) {
            throw new BadRequestException("Invalid id", ENTITY_NAME, "idnull");
        }
        Review result = reviewService.save(review);
        return ResponseEntity.ok()
                .body(result);
    }

    @GetMapping("/reviews")
    public List<Review> getAllReviews() {
        return reviewService.findAll();
    }

    @GetMapping("/reviews/{id}")
    public ResponseEntity<Review> getReview(@PathVariable Long id) {
        Optional<Review> review = reviewService.findOne(id);
        return review.map(response -> ResponseEntity.ok().body(response))
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @DeleteMapping("/reviews/{id}")
    public ResponseEntity<Void> deleteReview(@PathVariable Long id) {
        reviewService.delete(id);
        return ResponseEntity.noContent().build();
    }
}


