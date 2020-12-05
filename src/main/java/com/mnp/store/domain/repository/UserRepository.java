package com.mnp.store.domain.repository;

import com.mnp.store.contracts.dtos.BuyingStatistics;
import com.mnp.store.domain.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findOneByUsernameIgnoreCase(String username);

    Optional<User> findOneByEmailIgnoreCase(String email);

    Optional<User> findOneByUsernameOrEmailIgnoreCase(String username, String email);

    @EntityGraph(attributePaths = "roles")
    Optional<User> findOneWithRolesByEmailOrUsernameIgnoreCase(String email, String username);

    @EntityGraph(attributePaths = "roles")
    Optional<User> findOneWithRolesByUsernameIgnoreCase(String username);

    @Query(value = "select distinct user from User user left join fetch user.roles left join fetch user.reviews",
            countQuery = "select count(distinct user) from User user")
    Page<User> findAllWithRolesAndReviews(Pageable pageable);

    @Query("select distinct user from User user left join fetch user.roles left join fetch user.reviews")
    List<User> findAllWithRolesAndReview();

    @Query("select user from User user left join fetch user.roles left join fetch user.reviews where user.id =:id")
    Optional<User> findAllWithRolesAndReviews(@Param("id") Long id);

    @Query("select user.username, sum(purchase.total) as spending from User user inner join Purchase purchase on user = purchase.user group by user.id")
    BuyingStatistics getBuyingStatistics();
}
