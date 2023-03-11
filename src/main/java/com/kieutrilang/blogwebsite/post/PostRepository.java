package com.kieutrilang.blogwebsite.post;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PostRepository extends JpaRepository<Post,Long> {

    Optional<Post> findByCode(String code);

    Page<Post> findAll(Pageable pageable);

    Page<Post> findByAuthorUsername(String username, Pageable pageable);

    Page<Post> findByTopicName(String topicName,Pageable pageable);




}
