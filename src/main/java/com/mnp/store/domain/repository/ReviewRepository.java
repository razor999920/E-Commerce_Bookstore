package com.mnp.store.domain.repository;

import com.mnp.store.domain.Review;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Long> {

    @Query("select reviews from User user inner join user.reviews reviews where user.id=:userId and reviews.book.id=:bookId")
    List<Review> getByUserIdAndBookId(Pageable pageable, @Param("userId") Long userId, @Param("bookId") Long bookId);
}


