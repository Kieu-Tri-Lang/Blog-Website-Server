package com.kieutrilang.blogwebsite.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    Page<User> findAll(Pageable pageable);

    Page<User> findByUsernameContaining(String username,Pageable pageable);

    Optional<User> findByEmail(String email);

    Optional<User> findByUsername(String username);

}
