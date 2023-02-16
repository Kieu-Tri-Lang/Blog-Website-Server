package com.kieutrilang.blogwebsite.comment;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment,Long> {

    Page<Comment> findByParentId(Long id,Pageable pageable);

    Page<Comment> findByUserUsername(String username,Pageable pageable);

    Page<Comment> findAll(Pageable pageable);

    Page<Comment> findByPostCode(String code,Pageable pageable);

    Integer countByParentId(Long parentId);

}
