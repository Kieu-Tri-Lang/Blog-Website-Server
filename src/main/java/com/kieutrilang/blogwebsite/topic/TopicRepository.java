package com.kieutrilang.blogwebsite.topic;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface TopicRepository extends JpaRepository<Topic,Long> {

    Page<Topic> findAll(Pageable pageable);

    Optional<Topic> findByName(String name);
    @Query(value = """
            SELECT t
            FROM bw_topic t
            WHERE t.name LIKE '%:name%'
            ORDER BY
              CASE
                WHEN word LIKE ':name' THEN 1
                WHEN word LIKE ':name%' THEN 2
                WHEN word LIKE '%:name' THEN 4
                ELSE 3
              END""")
    Page<Topic> findTopicByNameContaining(@Param("name") String name, Pageable pageable);
}
