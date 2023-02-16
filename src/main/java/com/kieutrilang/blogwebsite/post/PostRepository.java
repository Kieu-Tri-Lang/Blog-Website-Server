package com.kieutrilang.blogwebsite.post;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PostRepository extends JpaRepository<Post,Long> {

    Optional<Post> findByCode(String code);
}
