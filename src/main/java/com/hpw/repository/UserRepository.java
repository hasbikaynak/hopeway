package com.hpw.repository;

import com.hpw.domain.User;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    @EntityGraph(attributePaths = "roles")
    Optional<User> findByEmail(final String email);

    List<User> getUserByEmailContaining(String email);

    @Query("SELECT u FROM User u WHERE u.userName LIKE %:userName%")
    Optional<User> findByUserName(String userName);

}
