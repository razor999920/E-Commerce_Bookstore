package com.mnp.store.domain.users;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findOneByUsernameIgnoreCase(String username);

    Optional<User> findOneByEmailIgnoreCase(String email);

    Optional<User> findOneByUsernameOrEmailIgnoreCase(String username, String email);

    @EntityGraph(attributePaths = "roles")
    Optional<User> findOneWithRolesByEmailIgnoreCase(String email);

    @EntityGraph(attributePaths = "roles")
    Optional<User> findOneWithRolesByUsernameIgnoreCase(String username);
}
