package com.mnp.store.services;

import com.mnp.store.contracts.ReviewService;
import com.mnp.store.domain.Review;
import com.mnp.store.domain.repository.ReviewRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class ReviewServiceImpl implements ReviewService {

    private final ReviewRepository reviewRepository;

    public ReviewServiceImpl(ReviewRepository reviewRepository) {
        this.reviewRepository = reviewRepository;
    }

    @Override
    public Review save(Review review) {
        return reviewRepository.save(review);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Review> findAll() {
        return reviewRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Review> findOne(Long id) {
        return reviewRepository.findById(id);
    }

    @Override
    public void delete(Long id) {
        reviewRepository.deleteById(id);
    }
}


