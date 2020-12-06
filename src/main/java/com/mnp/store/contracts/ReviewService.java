package com.mnp.store.contracts;

import com.mnp.store.domain.Review;

import java.util.List;
import java.util.Optional;

public interface ReviewService {

    Review save(Review review);

    List<Review> findAll();

    Optional<Review> findOne(Long id);

    void delete(Long id);

    Optional<Review> getUserReviewForBook(Long userId, Long bookId);
}


